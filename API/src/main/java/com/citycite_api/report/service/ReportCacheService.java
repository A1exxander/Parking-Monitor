package com.citycite_api.report.service;

import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ReportCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.report-ttl-minutes}")
    private String reportTTLMinutes;

}
