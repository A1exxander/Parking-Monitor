package com.citycite_api.report.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 3, max = 64)
    private String bucketName;

    @Column(nullable = false, unique = true)
    @Length(min = 3, max = 64)
    private String objectKey;

    @Column(columnDefinition = "ENUM('JPEG', 'PNG', 'WEBP', 'HEIC') NOT NULL")
    private ReportImageFormat imageFormat;

    @Column(nullable = false)
    private Integer imageSize;

    @Column(columnDefinition = "ENUM('LICENSE_PLATE', 'VIOLATION') NOT NULL")
    private ReportImageType imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportID", nullable = false)
    private Report report;

}
