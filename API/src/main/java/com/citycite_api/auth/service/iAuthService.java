package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.RegistrationRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iAuthService {
    void registerUser(RegistrationRequest registrationRequest);
}
