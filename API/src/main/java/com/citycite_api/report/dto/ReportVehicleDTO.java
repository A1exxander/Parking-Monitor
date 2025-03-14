package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportVehicleDTO { // DTO postfix is used to indicate it is used for both requests & responses

    @NotNull @Size(min = 2, max = 16)
    private final String manufacturer;

    @NotNull @Size(min = 2, max = 32)
    private final String model;

    @NotNull @Past
    private final Integer year;

    @Null @Size(max = 8)
    private final String color;

    @NotNull @Valid
    private final VehicleLicensePlateDTO vehicleLicensePlateDTO;

}
