package com.citycite_api.report.service;

import com.citycite_api.report.dto.AddressCoordinatesDTO;
import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import jakarta.transaction.Transactional;
import lombok.*;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class GeoReportCache implements iGeoReportCache {

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // May need to switch to Object

    @Value("${redis.report-ttl-minutes}") // TTL in hours
    private Integer reportTTLMinutes;

    @Value("${redis.keys.jurisdiction-base}")
    private String jurisdictionKeyBase = "reports:jurisdiction:";

    @Value("${redis.keys.report-expirations}")
    private String expirationKey = "reports:expirations";

    @Override
    public void cacheGeoReport(ReportResponse report) {

        String jurisdictionKey = jurisdictionKeyBase + report.getReportAddress().getJurisdiction().getID().toString();

        long expirationTimestamp = report.getCreatedAt().toEpochMilli() + TimeUnit.MINUTES.toMillis(reportTTLMinutes);
        if (expirationTimestamp <= System.currentTimeMillis()) {
            throw new IllegalArgumentException("Cannot cache a report that has already expired!");
        }

        Point reportPoint = new Point( // Maybe consider moving this into a GeoUtils class
                report.getReportAddress().getAddressCoordinates().getLongitude(),
                report.getReportAddress().getAddressCoordinates().getLatitude()
        );

        // With Redis, we cannot add a TTL to each val in a geoset, and we will need to cache expiration separately
        redisTemplate.opsForGeo().add(jurisdictionKey, reportPoint, report.getID().toString());
        redisTemplate.opsForZSet().add(expirationKey, report.getID().toString(), expirationTimestamp);

    }

    @Override
    public List<GeoReportResponse> getAllCachedGeoReports(Integer jurisdictionID) {

        Circle planetRadius = new Circle( // Create a radius large enough to cover the entire planet ( ~20,037 )
                new Point(0, 0),
                new Distance(20037, Metrics.KILOMETERS)
        );

        return getAllCachedGeoReports(jurisdictionID, planetRadius);

    }

    @Override
    public List<GeoReportResponse> getAllCachedGeoReports(Integer jurisdictionID, Circle radius) {

        String jurisdictionKey = jurisdictionKeyBase + jurisdictionID.toString();
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(jurisdictionKey, radius);

        if (results == null || results.getContent().isEmpty()) {
            return Collections.emptyList();
        }

        return results.getContent().stream().map(geoResult -> {

                    RedisGeoCommands.GeoLocation<String> location = geoResult.getContent();
                    String reportId = location.getName();
                    Point point = location.getPoint();
                    Double expirationTimestamp = redisTemplate.opsForZSet().score(expirationKey, reportId);

                    return new GeoReportResponse(
                            Integer.parseInt(reportId),
                            new AddressCoordinatesDTO(point.getX(), point.getY()),
                            Instant.ofEpochSecond(expirationTimestamp.longValue())
                    );

                })
                .collect(Collectors.toList());

    }

    @Override
    public void removeCachedGeoReport(Integer jurisdictionID, Integer reportID) { // reportID is unique, but since it's located inside a jurisdiction, we need it for O(logn) removals

        String jurisdictionKey = jurisdictionKeyBase + jurisdictionID.toString();
        redisTemplate.opsForGeo().remove(jurisdictionKey, reportID.toString());
        redisTemplate.opsForZSet().remove(expirationKey, reportID.toString());

    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    @SchedulerLock(name = "cleanupExpiredReports", lockAtLeastFor = "PT4M", lockAtMostFor = "PT5M")
    protected void cleanupExpiredGeoReports() { // I have no idea how any of this works but hope it works

        long now = System.currentTimeMillis();
        Set<String> expiredReportIds = redisTemplate.opsForZSet().rangeByScore(expirationKey, 0, now);

        if (expiredReportIds != null && !expiredReportIds.isEmpty()) {

            // Use a SCAN cursor to safely iterate over all geo keys
            try (Cursor<String> cursor = redisTemplate.scan(
                    ScanOptions.scanOptions()
                            .match(jurisdictionKeyBase + "*")
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
