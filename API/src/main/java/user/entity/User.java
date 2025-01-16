package user.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    @Length(min = 2, max = 32)
    private String firstName;

    @Column(nullable = false)
    @Length(min = 2, max = 32)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = true)
    @Length(max = 2048)
    private String profilePictureURL;

    @Column(columnDefinition = "ENUM('USER', 'OFFICER') NOT NULL DEFAULT 'USER'", updatable = false)
    private AccountType accountType = AccountType.USER;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

}
