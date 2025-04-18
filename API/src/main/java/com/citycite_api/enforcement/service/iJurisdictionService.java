package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.JurisdictionDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@Transactional
public interface iJurisdictionService {
    public boolean isJurisdictionSupported(String city, String stateInitials);
    public Set<JurisdictionDTO> getAllJurisdictions();
    public Page<JurisdictionDTO> findByCityStartingWith(Pageable pageable, String city);
}
