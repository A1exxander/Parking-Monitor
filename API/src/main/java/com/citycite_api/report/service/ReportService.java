package com.citycite_api.report.service;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.service.iOfficerService;
import com.citycite_api.report.dto.ReportGeoResponse;
import com.citycite_api.report.entity.ReportStatus;
import com.citycite_api.report.repository.iReportRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class ReportService implements iReportService {

    @Autowired
    private iOfficerService officerService;

    @Autowired
    private iReportGeoCache reportGeoCache;

    @Autowired
    private iReportRepository reportRepository;

    @Override
    public List<ReportGeoResponse> getReportGeoForOfficer(Integer officerID) {

        if (isOfficerRespondingToReport(officerID)) {
            throw new IllegalArgumentException("Officer is already responding to an in-process report!"); // Ideally we should throw custom exception
        }

        OfficerResponse officer = (OfficerResponse) officerService.findOfficerByID(officerID);
        Integer jurisdictionID = officer.getDepartment().getJurisdiction().getID();

        List<ReportGeoResponse> reportGeoResponseList = reportGeoCache.getAllCachedReportGeo(jurisdictionID);
        // Should filter by report types that our officers department can handle
        return reportGeoResponseList;

    }

    @Override
    public boolean isOfficerRespondingToReport(Integer officerID) {
        return reportRepository.findByReportStatusAndRespondingOfficerID(ReportStatus.IN_PROCESS, officerID);
    }

}
