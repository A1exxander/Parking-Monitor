package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportRequest {

    @Size(max = 256)
    private String violationDescription;

    @NotNull
    @Valid
    private ReportAddressDTO reportAddress;

    @NotNull
    @Valid
    private ReportVehicleDTO reportVehicle;

}
