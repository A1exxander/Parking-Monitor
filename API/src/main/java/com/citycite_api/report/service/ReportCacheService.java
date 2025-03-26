package com.citycite_api.report.service;

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
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    private String reportExpirationKey = "reports:expirations";

    public void cacheReport(ReportResponse report) {

        String jurisdictionKey = baseGeoKey + report.getReportAddress().getJurisdictionID().toString();

        // Compute the absolute expiration timestamp in milliseconds
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
        redisTemplate.opsForZSet().add(reportExpirationKey, report.getReportID().toString(), expirationTimestamp);

    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    @SchedulerLock(name = "cleanupExpiredReports", lockAtLeastFor = "PT4M", lockAtMostFor = "PT5M")
    protected void cleanupExpiredReports() { // I have no idea how any of this works but hope it works

        long now = System.currentTimeMillis();
        Set<String> expiredReportIds = redisTemplate.opsForZSet().rangeByScore(reportExpirationKey, 0, now);

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
            redisTemplate.opsForZSet().removeRangeByScore(reportExpirationKey, 0, now);
        }
    }

}
