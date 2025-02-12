package com.citycite_api.enforcement.mapper;

import com.citycite_api.enforcement.entity.Jurisdiction;
import com.citycite_api.enforcement.dto.JurisdictionResponse;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface iJurisdictionMapper {
    public Set<JurisdictionResponse> jurisdictionsToJurisdictionResponses(Set<Jurisdiction> jurisdictions);
    public JurisdictionResponse jurisdictionToJurisdictionResponse(Jurisdiction jurisdiction);
}

