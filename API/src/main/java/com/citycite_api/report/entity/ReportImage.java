package com.citycite_api.report.entity;

import jakarta.persistence.*;

@Entity
@Table
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false, unique = true)
    private String imageURL;

    @Column(columnDefinition = "ENUM('LICENSE_PLATE', 'VIOLATION') NOT NULL")
    private ReportImageType imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportID", nullable = false)
    private Report report;

}
