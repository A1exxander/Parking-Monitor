package auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import user.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Table
@Entity
@Data
public class UserCredentials {

    @Id
    private Integer ID;

    @Column(nullable = false, unique = true)
    @Length(min = 8, max = 320)
    @Email
    private String emailAddress;

    @Column(nullable = false)
    @Length(min = 8, max = 256)
    private String hashedPassword;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID")
    private User user;

}
