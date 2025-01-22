package enforcement.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import java.sql.Timestamp;

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 3, max = 32)
    private String name;

    @Column(columnDefinition = "ENUM('POLICE', 'PARKING_AUTHORITY') NOT NULL")
    private DepartmentType departmentType;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = true)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JoinColumn(name = "JurisdictionID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Jurisdiction jurisdiction;

}
