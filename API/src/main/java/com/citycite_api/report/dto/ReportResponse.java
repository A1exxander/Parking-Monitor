package com.citycite_api.report.dto;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.report.entity.ResolutionStatus;
import com.citycite_api.user.dto.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.Instant;

@Data
public class ReportResponse {

    @NotNull
    @Min(1)
    private Integer ID;

    @NotNull
    @Valid
    private UserResponse submittingUser;

    @Null
    @Size(max = 256)
    private String violationDescription;

    @NotNull
    @Valid
    private ReportAddressDTO reportAddress;

    @NotNull
    @Valid
    private ReportVehicleDTO reportVehicle;

    @Null
    private OfficerResponse respondingOfficer;

    @Null
    private ResolutionStatus resolutionStatus;

    @Null
    @Size(max = 64)
    private String resolutionNotes;

    @NotNull
    @Past
    private Instant createdAt;

    @Null
    private Instant updatedAt;

}
