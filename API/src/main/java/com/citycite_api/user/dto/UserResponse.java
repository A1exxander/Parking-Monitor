package com.citycite_api.user.dto;

import com.citycite_api.user.entity.AccountType;
import lombok.*;
import java.util.Date;

@Data
public class UserResponse {
        private Integer ID;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private AccountType accountType;
        private String profilePictureURL;
        private Date createdAt;
        private Date updatedAt;
}
