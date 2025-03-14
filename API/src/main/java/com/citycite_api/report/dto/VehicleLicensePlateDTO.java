package com.citycite_api.report.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleLicensePlateDTO {

    @NotNull
    @Size(min = 2, max = 8)
    private final String number;

    @NotNull @Size
    private final String stateInitials;

}
