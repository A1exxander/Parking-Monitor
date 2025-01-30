package com.citycite_api.enforcement.entity;

import com.citycite_api.report.entity.ReportAddress;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.util.Set;

@Entity
@Table
@Data
public class Jurisdiction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 2, max = 32)
    private String city;

    @Column(nullable = false)
    @Length(min = 2, max = 2)
    private String stateInitials;

    @OneToMany(mappedBy = "jurisdiction", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> departments;

    @OneToMany(mappedBy = "jurisdiction", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReportAddress> reportAddresses;

}
