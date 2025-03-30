package com.citycite_api.report.service;

import com.citycite_api.report.dto.ReportMapResponse;
import com.citycite_api.report.dto.ReportResponse;
import org.springframework.data.geo.Circle;
import java.util.List;

public interface iReportCacheService {
    public void cacheReport(ReportResponse report);
    public List<ReportMapResponse> getAllCachedReports(Integer jurisdictionID);
    public List<ReportMapResponse> getAllCachedReports(Integer jurisdictionID, Circle radius);
    public void removeCachedReport(Integer jurisdictionID, Integer reportID);
}
