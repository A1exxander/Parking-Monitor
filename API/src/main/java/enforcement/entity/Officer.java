package enforcement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import user.entity.User;

@Entity
@Table
@Data
public class Officer extends User {

    @Column(nullable = false)
    @Length(min = 3, max = 16)
    private String identificationNumber;

    @JoinColumn(name = "DepartmentID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;


}
