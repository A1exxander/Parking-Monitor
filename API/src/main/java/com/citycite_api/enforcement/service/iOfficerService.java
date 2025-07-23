package com.citycite_api.enforcement.service;

import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.entity.Officer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iOfficerService {
    public OfficerResponse getOfficerByID(Integer ID);
    public Officer getOfficerProxyByID(Integer ID);
}
