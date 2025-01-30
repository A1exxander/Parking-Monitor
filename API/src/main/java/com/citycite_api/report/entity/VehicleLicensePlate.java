package com.citycite_api.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table
@Data
public class VehicleLicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 2, max = 8)
    private String number;

    @Column(nullable = false)
    @Length(min = 2, max = 2)
    private String stateInitials;

    @OneToOne(mappedBy = "vehicleLicensePlate", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportVehicle reportVehicle;

}
