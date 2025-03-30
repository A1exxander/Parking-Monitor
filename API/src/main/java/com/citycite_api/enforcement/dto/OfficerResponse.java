package com.citycite_api.enforcement.dto;

import com.citycite_api.enforcement.entity.Department;
import com.citycite_api.user.dto.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OfficerResponse extends UserResponse {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 16)
    private String identificationNumber;

    @NotNull
    @Valid
    private Department department;

}
