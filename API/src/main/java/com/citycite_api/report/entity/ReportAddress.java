package com.citycite_api.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.citycite_api.enforcement.entity.Jurisdiction;

@Entity
@Table
@Data
public class ReportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JurisdictionID", nullable = false)
    private Jurisdiction jurisdiction;

    @Column(nullable = false, length = 9)
    private String zipcode;

    @Embedded
    private Coordinates coordinates;

    @Column(length = 128)
    private String notes;

    @OneToOne(mappedBy = "reportAddress")
    private Report report;

}
