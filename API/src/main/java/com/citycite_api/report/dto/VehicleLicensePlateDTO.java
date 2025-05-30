package com.citycite_api.report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VehicleLicensePlateDTO {

    private Integer ID;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 8)
    private String number;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String stateInitials;

}
