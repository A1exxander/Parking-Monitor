package com.citycite_api.report.entity;

import com.citycite_api.enforcement.entity.Officer;
import com.citycite_api.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import java.sql.Timestamp;

@Entity
@Table
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false)
    private User submittingUser;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "VehicleID", nullable = false)
    private ReportVehicle reportVehicle;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AddressID", nullable = false)
    private ReportAddress reportAddress;

    @Column(nullable = true)
    @Length(max = 256)
    private String violationDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RespondingOfficerID", nullable = true)
    private Officer respondingOfficer;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

}
