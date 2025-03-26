package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportRequest {

    @Null @Size(max = 256)
    private final String violationDescription;

    @NotNull @Valid
    private final ReportAddressDTO reportAddress;

    @NotNull @Valid
    private final ReportVehicleDTO reportVehicle;

}
