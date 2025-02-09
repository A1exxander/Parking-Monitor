package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.user.dto.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iAuthService {
    public void registerUser(UserRequest userRequest, CredentialsRequest credentialsRequest);
}
