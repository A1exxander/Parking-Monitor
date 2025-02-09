package com.citycite_api.auth.dto;

import com.citycite_api.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequest {
    @Valid @NotNull
    private final UserRequest userRequest;
    @Valid @NotNull
    private final CredentialsRequest credentialsRequest;
}
