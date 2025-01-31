package com.citycite_api.enforcement.entity;

import com.citycite_api.report.entity.Report;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.citycite_api.user.entity.User;
import java.util.List;

@Entity
@Table
@DiscriminatorValue("OFFICER")
@Data
public class Officer extends User {

    @Column(nullable = false)
    @Length(min = 3, max = 16)
    private String identificationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DepartmentID", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "respondingOfficer", fetch = FetchType.LAZY)
    private List<Report> respondedReports;

}
