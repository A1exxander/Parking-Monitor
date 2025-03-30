package com.citycite_api.enforcement.mapper;

import com.citycite_api.enforcement.entity.Jurisdiction;
import com.citycite_api.enforcement.dto.JurisdictionDTO;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface iJurisdictionMapper {
    public JurisdictionDTO jurisdictionToJurisdictionResponse(Jurisdiction jurisdiction);
    public Set<JurisdictionDTO> jurisdictionsToJurisdictionResponses(Set<Jurisdiction> jurisdictions);
}


