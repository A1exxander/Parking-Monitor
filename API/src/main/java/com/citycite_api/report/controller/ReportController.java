package com.citycite_api.report.controller;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import com.citycite_api.report.service.iReportService;
import com.citycite_api.user.entity.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/api/v1/reports")
@Validated
@AllArgsConstructor
public class ReportController implements iReportController {

    @Autowired
    private iReportService reportService;

    @Override
    @GetMapping("/geo")
    public ResponseEntity<List<GeoReportResponse>> getAllGeoReports(@NotNull Authentication authentication) {

        Integer currentOfficerID = (Integer) authentication.getPrincipal();
        List<GeoReportResponse> geoReportResponseList = reportService.getReportGeoForOfficer(currentOfficerID);
        return ResponseEntity.ok(geoReportResponseList);

    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ReportResponse>> getReports(@NotNull Authentication authentication,
                                                           @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {

        // Extract authenticated user information
        Integer userID = (Integer) authentication.getPrincipal();
        AccountType accountType = AccountType.valueOf(authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replaceFirst("^ROLE_", "")
                .toUpperCase()); // Should prob move this into its own method, and use ROLE_ for account types in future

        Page<ReportResponse> reportsResponse = null;

        switch (accountType) {

            case USER ->
                    reportsResponse = reportService.getReportsWithSubmittingUserID(pageable, userID);
            case OFFICER ->
                    reportsResponse = reportService.getReportsWithRespondingOfficerID(pageable, userID);
            default ->
                    throw new IllegalArgumentException("Unsupported account type!");

        };

        return ResponseEntity.ok(reportsResponse);

    }


}
