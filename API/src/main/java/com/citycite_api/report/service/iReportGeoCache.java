package com.citycite_api.report.service;

import com.citycite_api.report.dto.ReportGeoResponse;
import com.citycite_api.report.dto.ReportResponse;
import org.springframework.data.geo.Circle;
import java.util.List;

public interface iReportGeoCache {
    public void cacheReportGeo(ReportResponse report);
    public List<ReportGeoResponse> getAllCachedReportGeo(Integer jurisdictionID);
    public List<ReportGeoResponse> getAllCachedReportGeo(Integer jurisdictionID, Circle radius);
    public void removeCachedReportGeo(Integer jurisdictionID, Integer reportID);
}
