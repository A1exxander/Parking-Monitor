package com.citycite_api.report.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressCoordinatesDTO {

    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private double latitude;

    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private double longitude;

}
