package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.JurisdictionDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public boolean isJurisdictionSupported(String city, String stateInitials) {
        return jurisdictionRepository.existsByCityIgnoreCaseAndStateInitialsIgnoreCase(city, stateInitials);
    }

    @Override
    public Set<JurisdictionDTO> getAllJurisdictions() {
        Set<Jurisdiction> jurisdictions = jurisdictionRepository.findBy();
        return jurisdictionMapper.jurisdictionsToJurisdictionResponses(jurisdictions);
    }

    @Override
    public Page<JurisdictionDTO> findByCityStartingWith(Pageable pageable, String city) {
        Page<Jurisdiction> jurisdictionPage = jurisdictionRepository.findByCityStartsWith(pageable, city);
        return jurisdictionPage.map(jurisdictionMapper::jurisdictionToJurisdictionResponse);
    }

}
