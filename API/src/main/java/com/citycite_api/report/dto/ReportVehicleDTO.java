package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportVehicleDTO { // DTO postfix is used to indicate it is used for both requests & responses

    private Integer ID;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 16)
    private String manufacturer;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 32)
    private String model;

    @NotNull
    @Min(1885)
    private Integer year;

    @Size(max = 8)
    private String color;

    @NotNull
    @Valid
    private VehicleLicensePlateDTO vehicleLicensePlate;

}
