package com.citycite_api.report.controller;

import com.citycite_api.report.dto.GeoReportResponse;
import com.citycite_api.report.dto.ReportResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public interface iReportController {

    @GetMapping("/geo")
    public ResponseEntity<List<GeoReportResponse>> getAllGeoReports(@NotNull Authentication authentication);


    @GetMapping
    public ResponseEntity<Page<ReportResponse>> getReports(@NotNull Authentication authentication,
                                                    @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable);

    @GetMapping("/{reportID}")
    public ResponseEntity<ReportResponse> getReport(@NotNull Authentication authentication, @PathVariable @NotNull @Min(1) Integer reportID);

}
