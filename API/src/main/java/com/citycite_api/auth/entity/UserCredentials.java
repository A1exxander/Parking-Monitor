package com.citycite_api.auth.entity;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import com.citycite_api.user.entity.User;

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

    @JoinColumn(name = "ID", nullable = false)
    @OneToOne
    @MapsId
    private User user;

}
