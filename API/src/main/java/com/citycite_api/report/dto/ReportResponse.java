package com.citycite_api.report.dto;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.report.entity.ResolutionStatus;
import com.citycite_api.user.dto.UserResponse;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class ReportResponse {

    private Integer reportID;
    private UserResponse submittingUser;
    private String violationDescription;
    private ReportAddressDTO reportAddress;
    private ReportVehicleDTO reportVehicle;
    private OfficerResponse respondingOfficer;
    private ResolutionStatus resolutionStatus;
    private String resolutionNotes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
