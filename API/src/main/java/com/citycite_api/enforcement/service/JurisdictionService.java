package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.JurisdictionResponse;
import com.citycite_api.enforcement.entity.Jurisdiction;
import com.citycite_api.enforcement.mapper.iJurisdictionMapper;
import com.citycite_api.enforcement.repository.iJurisdictionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class JurisdictionService implements iJurisdictionService {

    @Autowired
    private iJurisdictionRepository jurisdictionRepository;

    @Autowired
    private iJurisdictionMapper jurisdictionMapper;

    @Override
    public Set<JurisdictionResponse> getAll() {
        Set<Jurisdiction> jurisdictions = jurisdictionRepository.findAll();
        return jurisdictionMapper.jurisdictionsToJurisdictionResponses(jurisdictions);
    }

    @Override
    public boolean isSupported(@Size(min = 2, max = 32) @NotNull String city, @Size(min = 2, max = 2) @NotNull String stateInitials) {
        return jurisdictionRepository.existsByCityIgnoreCaseAndStateInitialsIgnoreCase(city, stateInitials);
    }

}
