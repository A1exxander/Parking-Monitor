package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.user.dto.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public interface iAuthService {
    public void register(UserRequest userRequest, CredentialsRequest credentialsRequest, MultipartFile profilePicture);
    public String login(CredentialsRequest credentialsRequest);
}
