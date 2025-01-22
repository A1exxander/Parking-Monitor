package enforcement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
