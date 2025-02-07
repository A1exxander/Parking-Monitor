package com.citycite_api.auth.dto;

import com.citycite_api.user.dto.UserRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotNull
    private UserRequest userRequest;

    @NotNull
    private CredentialsDTO credentialsDTO;

}
