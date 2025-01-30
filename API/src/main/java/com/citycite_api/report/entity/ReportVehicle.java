package com.citycite_api.report.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table
@Data
public class ReportVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 2, max = 16)
    private String manufacturer;

    @Column(nullable = false)
    @Length(min = 2, max = 32)
    private String model;

    @Column(nullable = false)
    @Min(1885)
    private Integer year;

    @Column
    @Length(max = 8)
    private String color;

    @OneToOne
    @JoinColumn(name = "LicensePlateID", nullable = false)
    private VehicleLicensePlate vehicleLicensePlate;

}
