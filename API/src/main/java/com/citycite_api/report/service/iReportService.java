package com.citycite_api.report.service;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import com.citycite_api.user.entity.AccountType;
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
    public List<GeoReportResponse> getGeoReports(Integer officerID);
    public boolean isOfficerRespondingToReport(Integer officerID);
    public Page<ReportResponse> getReportsByRespondingOfficerID(Pageable pageable, Integer officerID);
    public Page<ReportResponse> getReportsBySubmittingUserID(Pageable pageable, Integer userID);
    public Page<ReportResponse> getReportsForUser(Pageable pageable, Integer userID, AccountType accountType);
    public ReportResponse getReportByID(Integer reportID, Integer userID, AccountType accountType);
}
