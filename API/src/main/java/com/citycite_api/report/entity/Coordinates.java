package com.citycite_api.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Embeddable
@Data
public class Coordinates {

    @Column(nullable = false)
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private double longitude;

    @Column(nullable = false)
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private double latitude;

}
