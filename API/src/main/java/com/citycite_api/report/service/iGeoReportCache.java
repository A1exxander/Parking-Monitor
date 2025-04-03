package com.citycite_api.report.service;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface iGeoReportCache {
    public void cacheGeoReport(ReportResponse report);
    public List<GeoReportResponse> getAllCachedGeoReports(Integer jurisdictionID);
    public List<GeoReportResponse> getAllCachedGeoReports(Integer jurisdictionID, Circle radius);
    public void removeCachedGeoReport(Integer jurisdictionID, Integer reportID);
}
