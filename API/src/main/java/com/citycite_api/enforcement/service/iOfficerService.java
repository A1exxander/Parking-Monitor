package com.citycite_api.enforcement.service;

import com.citycite_api.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iOfficerService {
    public UserResponse findOfficerByID(Integer ID);
}
