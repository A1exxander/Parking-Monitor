package com.citycite_api.report.controller;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.service.iReportService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<List<GeoReportResponse>> getAllGeoReports(@NotNull Authentication authentication) {

        Integer currentOfficerID = (Integer) authentication.getPrincipal();
        List<GeoReportResponse> geoReportResponseList = reportService.getReportGeoForOfficer(currentOfficerID);
        return ResponseEntity.ok(geoReportResponseList);

    }

}
