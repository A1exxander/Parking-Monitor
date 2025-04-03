package com.citycite_api.report.service;

import com.citycite_api.report.dto.ReportGeoResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public interface iReportService {
    List<ReportGeoResponse> getReportGeoForOfficer(Integer userID);
    public boolean isOfficerRespondingToReport(Integer officerID);
}
