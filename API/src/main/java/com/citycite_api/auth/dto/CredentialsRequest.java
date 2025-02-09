package com.citycite_api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CredentialsRequest {

    @NotNull @Email
    @Size(min = 8, max = 320)
    private final String emailAddress;

    @NotNull
    @Size(min = 8, max = 256)
    private final String password;

}
