package com.citycite_api.report.service;

import com.citycite_api.report.dto.AddressCoordinatesDTO;
import com.citycite_api.report.dto.ReportMapResponse;
import com.citycite_api.report.dto.ReportResponse;
import jakarta.transaction.Transactional;
import lombok.*;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.geo.Point;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // May need to switch to Object

    @Value("${redis.report-ttl-minutes}") // TTL in hours
    private Integer reportTTLMinutes;

    private String baseGeoKey = "reports:geo:";
    private String expirationKey = "reports:expirations";

    public void cacheReport(ReportResponse report) {

        String jurisdictionKey = baseGeoKey + report.getReportAddress().getJurisdictionID().toString();

        long expirationTimestamp = report.getCreatedAt().toInstant().toEpochMilli() + TimeUnit.MINUTES.toMillis(reportTTLMinutes);
        if (expirationTimestamp <= System.currentTimeMillis()) {
            throw new IllegalArgumentException("Cannot cache expired report!");
        }

        Point reportPoint = new Point(
                report.getReportAddress().getAddressCoordinates().getLongitude(),
                report.getReportAddress().getAddressCoordinates().getLatitude()
        );

        // With Redis, we cannot add a TTL to each val in a geoset, and we will need to cache expiration separately
        redisTemplate.opsForGeo().add(jurisdictionKey, reportPoint, report.getReportID().toString());
        redisTemplate.opsForZSet().add(expirationKey, report.getReportID().toString(), expirationTimestamp);

    }

    public List<ReportMapResponse> getAllCachedReports(Integer jurisdictionID) {

        String jurisdictionKey = baseGeoKey + jurisdictionID.toString();
        Set<String> members = redisTemplate.opsForZSet().range(jurisdictionKey, 0, -1);

        if (members == null || members.isEmpty()) {
            return Collections.emptyList();
        }

        return members.stream()
                .map(member -> {
                    Point point = redisTemplate.opsForGeo().position(jurisdictionKey, member).get(0);
                    return new ReportMapResponse(
                            Integer.parseInt(member),  // Use String ID directly
                            new AddressCoordinatesDTO(
                                    point.getX(),  // longitude
                                    point.getY()   // latitude
                            )
                    );
                })
                .collect(Collectors.toList());

    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
    @SchedulerLock(name = "cleanupExpiredReports", lockAtLeastFor = "PT4M", lockAtMostFor = "PT5M")
    protected void cleanupExpiredReports() { // I have no idea how any of this works but hope it works

        long now = System.currentTimeMillis();
        Set<String> expiredReportIds = redisTemplate.opsForZSet().rangeByScore(expirationKey, 0, now);

        if (expiredReportIds != null && !expiredReportIds.isEmpty()) {

            // Use a SCAN cursor to safely iterate over all geo keys
            try (Cursor<String> cursor = redisTemplate.scan(
                    ScanOptions.scanOptions()
                            .match(baseGeoKey + "*")
                            .count(100)
                            .build()
            )) {
                while (cursor.hasNext()) {
                    String jurisdictionKey = cursor.next();
                    for (String reportId : expiredReportIds) {
                        redisTemplate.opsForZSet().remove(jurisdictionKey, reportId);
                    }
                }
            }

            redisTemplate.opsForZSet().removeRangeByScore(expirationKey, 0, now);

        }
    }

}
