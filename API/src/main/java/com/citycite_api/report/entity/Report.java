package com.citycite_api.report.entity;

import com.citycite_api.enforcement.entity.Officer;
import com.citycite_api.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

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

    @Column(columnDefinition = "ENUM('APPROVED', 'DENIED') NULL")
    private ResolutionStatus resolutionStatus;

    @Column(nullable = true)
    private String resolutionNotes;

    @OneToMany(mappedBy = "report", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReportImage> reportImages;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column
    @UpdateTimestamp
    private Instant updatedAt;

    public ReportStatus getReportStatus() {

        if (respondingOfficer == null && !isTimedOut()){
            return ReportStatus.OPEN;
        }
        else if (respondingOfficer != null && !isTimedOut() && resolutionStatus == null){
            return ReportStatus.IN_PROCESS;
        }
        else if (isTimedOut() && resolutionStatus == null) {
            return ReportStatus.TIMED_OUT;
        }
        else {
            return ReportStatus.RESOLVED;
        }

    }

    private boolean isTimedOut() {
        return createdAt == null ? false : Duration.between(createdAt, Instant.now()).toHours() >= 2;
    }

}
