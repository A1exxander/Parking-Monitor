package com.citycite_api.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.math.BigDecimal;

@Embeddable
@Data
public class Coordinates {

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

}
