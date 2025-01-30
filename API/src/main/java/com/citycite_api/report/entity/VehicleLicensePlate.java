package com.citycite_api.report.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.validator.constraints.Length;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"number", "stateInitials"} // Composite unique constraint
))
@NaturalIdCache
@Data
public class VehicleLicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @NaturalId
    @Column(nullable = false)
    @Length(min = 2, max = 8)
    private String number;

    @NaturalId
    @Column(nullable = false)
    @Length(min = 2, max = 2)
    private String stateInitials;

    @OneToMany(mappedBy = "vehicleLicensePlate", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReportVehicle> reportVehicles;

}
