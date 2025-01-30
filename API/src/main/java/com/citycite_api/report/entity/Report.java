package com.citycite_api.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.sql.Timestamp;

@Entity
@Table
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "VehicleID", nullable = false)
    private ReportVehicle reportVehicle;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AddressID", nullable = false)
    private ReportAddress reportAddress;

    @Column
    @Length(max = 256)
    private String violationDescription;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

}
