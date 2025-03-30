package com.citycite_api.user.dto;

import com.citycite_api.user.entity.AccountType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;

@Data
public class UserResponse {

        @NotNull
        @Min(1)
        private Integer ID;

        @NotNull
        @NotBlank
        @Size(min = 2, max = 32)
        private String firstName;

        @NotNull
        @NotBlank
        @Size(min = 2, max = 32)
        private String lastName;

        @NotNull
        private Date birthDate;

        @NotNull
        private AccountType accountType;

        private String profilePictureURL;

        @NotNull
        @PastOrPresent
        private Date createdAt;

        @NotNull
        @PastOrPresent
        private Date updatedAt;

}
