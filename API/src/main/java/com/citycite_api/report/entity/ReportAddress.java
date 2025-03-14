package com.citycite_api.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.citycite_api.enforcement.entity.Jurisdiction;
import org.hibernate.validator.constraints.Length;

@Entity
@Table
@Data
public class ReportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 4, max = 64)
    private String streetLine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JurisdictionID", nullable = false)
    private Jurisdiction jurisdiction;

    @Column(nullable = false)
    @Length(min = 5, max = 9)
    private String zipcode;

    @Embedded
    private Coordinates coordinates;

    @Column(length = 128)
    private String notes;

    @OneToOne(mappedBy = "reportAddress")
    private Report report;

}
