package com.citycite_api.report.service;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public interface iReportService {
    @PreAuthorize("hasRole('OFFICER')")
    public List<GeoReportResponse> getReportGeoForOfficer(Integer officerID);
    public boolean isOfficerRespondingToReport(Integer officerID);
    public Page<ReportResponse> getReportsWithRespondingOfficerID(Pageable pageable, Integer officerID);
    public Page<ReportResponse> getReportsWithSubmittingUserID(Pageable pageable, Integer userID);
}
