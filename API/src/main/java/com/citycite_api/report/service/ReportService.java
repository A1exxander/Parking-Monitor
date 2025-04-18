package com.citycite_api.report.service;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.service.iOfficerService;
import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import com.citycite_api.report.entity.Report;
import com.citycite_api.report.entity.ReportStatus;
import com.citycite_api.report.mapper.iReportMapper;
import com.citycite_api.report.repository.iReportRepository;
import com.citycite_api.user.entity.AccountType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ReportService implements iReportService {

    @Autowired
    private iOfficerService officerService;

    @Autowired
    private iGeoReportCache geoReportCache;

    @Autowired
    private iReportRepository reportRepository;

    @Autowired
    private iReportMapper reportMapper;

    @Override
    @PreAuthorize("hasRole('OFFICER')")
    public List<GeoReportResponse> getGeoReports(Integer officerID) {

        if (isOfficerRespondingToReport(officerID)) {
            throw new IllegalArgumentException("Officer is already responding to an in-process report!"); // Ideally we should throw custom exception
        }

        OfficerResponse officer = officerService.getOfficerByID(officerID); // User service then casting it also works
        Integer jurisdictionID = officer.getDepartment().getJurisdiction().getID();

        // Should filter by report types that our officer's department can handle in the future
        return geoReportCache.getAllCachedGeoReports(jurisdictionID);

    }

    @Override
    public boolean isOfficerRespondingToReport(Integer officerID) {
        return reportRepository.findByReportStatusAndRespondingOfficerID(ReportStatus.IN_PROCESS, officerID);
    }

    @Override
    public Page<ReportResponse> findReportsByRespondingOfficerID(Pageable pageable, Integer officerID) {
        Page<Report> reportPage = reportRepository.findAllByRespondingOfficer_ID(pageable, officerID);
        return reportPage.map(reportMapper::reportToReportResponse);
    }

    @Override
    public Page<ReportResponse> findReportsBySubmittingUserID(Pageable pageable, Integer userID) {
        Page<Report> reportPage = reportRepository.findAllBySubmittingUser_ID(pageable, userID);
        return reportPage.map(reportMapper::reportToReportResponse);
    }

    @Override
    public Page<ReportResponse> getReportsForUser(Pageable pageable, Integer userID, AccountType accountType) { // Can maybe use better name

        Page<ReportResponse> reportsResponse = null;

        switch (accountType) { // We can ensure account type from controller is valid

            case USER ->
                    reportsResponse = findReportsBySubmittingUserID(pageable, userID);
            case OFFICER ->
                    reportsResponse = findReportsByRespondingOfficerID(pageable, userID);
            default ->
                    throw new IllegalStateException("Unsupported account type!");

        }

        return reportsResponse;

    }

    @Override
    public ReportResponse getReportByID(Integer reportID, Integer userID, AccountType accountType) {

        Report report = null;

        switch (accountType) {

            case USER ->
                    report = reportRepository.findBySubmittingUser_ID(reportID).orElseThrow(() -> new NoSuchElementException("Report with the ID " + reportID+ " does not exist."));
            case OFFICER -> {
                report = reportRepository.findById(reportID).orElseThrow(() -> new NoSuchElementException("Report with the ID " + reportID + " does not exist."));
                if (report.getRespondingOfficer() != null && report.getRespondingOfficer().getID() != userID){
                    throw new SecurityException("Report has a different responding officer ID.");
                }
            }
            default ->
                    throw new IllegalStateException("Unsupported account type!");
        }

        return reportMapper.reportToReportResponse(report);

    }

}
