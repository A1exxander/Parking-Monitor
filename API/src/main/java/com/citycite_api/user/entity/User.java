package com.citycite_api.user.entity;

import com.citycite_api.auth.entity.UserCredentials;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = true)
    @Length(max = 2048)
    private String profilePictureURL;

    @Column(columnDefinition = "ENUM('USER', 'OFFICER') NOT NULL DEFAULT 'USER'", updatable = false)
    private AccountType accountType = AccountType.USER;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = true)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserCredentials credentials;

}
