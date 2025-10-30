package com.citycite_api.report.controller;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportRequest;
import com.citycite_api.report.dto.ReportResponse;
import com.citycite_api.report.service.iReportService;
import com.citycite_api.security.utils.SecurityUtils;
import com.citycite_api.user.entity.AccountType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
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
        List<GeoReportResponse> geoReportResponseList = reportService.getGeoReports(currentOfficerID);
        return ResponseEntity.ok(geoReportResponseList);

    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ReportResponse>> getReports(@NotNull Authentication authentication, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {

        // Extract authenticated user information
        Integer userID = (Integer) authentication.getPrincipal();
        AccountType accountType = SecurityUtils.extractUserRole(authentication);

        Page<ReportResponse> reportsResponse = reportService.findReportsByUserIDAndAccountType(pageable, userID, accountType);
        return ResponseEntity.ok(reportsResponse);

    }

    @Override
    @GetMapping("/{reportID}")
    public ResponseEntity<ReportResponse> getReport(@NotNull Authentication authentication, @PathVariable @NotNull @Min(1) Integer reportID) {

        Integer userID = (Integer) authentication.getPrincipal();
        AccountType accountType = SecurityUtils.extractUserRole(authentication);

        ReportResponse reportResponse = reportService.getReportByID(reportID, userID, accountType);
        return ResponseEntity.ok(reportResponse);

    }

    @Override
    @PostMapping
    public ResponseEntity<Void> createReport(@NotNull Authentication authentication, @RequestBody @Valid ReportRequest reportRequest) {
        reportService.createReport(reportRequest, (Integer) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{reportID}/respondingOfficer")
    public ResponseEnttiy<Void> acceptReport(@NotNull Authentication authentication, @PathVariable @NotNull @Min(1) Integer reportID) {
        Integer userID = (Integer) authentication.getPrincipal();
        reportService.acceptReport(reportID, userID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build() 
    }

}
