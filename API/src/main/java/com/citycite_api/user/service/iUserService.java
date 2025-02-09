package com.citycite_api.user.service;

import com.citycite_api.user.dto.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iUserService {
    public void createUser(UserRequest userRequest, String emailAddress, String hashedPassword);
}
