package com.citycite_api.enforcement.dto;

import com.citycite_api.enforcement.entity.Department;
import com.citycite_api.user.dto.UserResponse;
import lombok.Data;

@Data
public class OfficerResponse extends UserResponse {

    private String identificationNumber;
    private Department department;

}
