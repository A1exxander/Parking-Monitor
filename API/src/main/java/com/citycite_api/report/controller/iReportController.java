package com.citycite_api.report.controller;

import com.citycite_api.report.dto.ReportGeoResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public interface iReportController {

    @GetMapping("/geo")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<List<ReportGeoResponse>> getAllReportGeo(@NotNull Authentication authentication);

}
