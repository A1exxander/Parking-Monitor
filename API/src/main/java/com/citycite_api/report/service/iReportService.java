package com.citycite_api.report.service;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportRequest;
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

    @PreAuthorize("hasRole('USER')")
    public void createReport(ReportRequest reportRequest, Integer userID);

    public boolean isOfficerRespondingToReport(Integer officerID);
    public boolean isReportVehicleInCooldownPeriod(String number, String stateInitials);
    public Page<ReportResponse> findReportsByRespondingOfficerID(Pageable pageable, Integer officerID);
    public Page<ReportResponse> findReportsBySubmittingUserID(Pageable pageable, Integer userID);
    public Page<ReportResponse> findReportsByUserIDAndAccountType(Pageable pageable, Integer userID, AccountType accountType);
    public ReportResponse getReportByID(Integer reportID, Integer userID, AccountType accountType);

}
