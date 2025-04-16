package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.OfficerResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iOfficerService {
    public OfficerResponse findOfficerByID(Integer ID);
}
