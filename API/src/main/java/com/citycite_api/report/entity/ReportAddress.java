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
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JurisdictionID", nullable = false)
    private Jurisdiction jurisdiction;

    @Column(name = "Zipcode", nullable = false, length = 9)
    private String zipcode;

    @Embedded
    private Coordinates coordinates;

    @Column(name = "Notes", length = 128)
    private String notes;

}
