package com.citycite_api.user.dto;

import com.citycite_api.user.entity.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor(force = true)
public class UserResponse {
        private final Integer ID;
        private final String firstName;
        private final String lastName;
        private final Date birthDate;
        private final AccountType accountType;
        private final String profilePictureURL;
        private final Date createdAt;
        private final Date updatedAt;
}
