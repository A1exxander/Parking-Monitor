package com.citycite_api.enforcement.mapper;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.entity.Officer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface iOfficerMapper {
    OfficerResponse officerToOfficerResponse(Officer officer);
}