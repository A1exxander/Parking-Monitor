package com.citycite_api.user.service;

import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iUserService {
    public void createUser(UserRequest userRequest, String emailAddress, String hashedPassword);
    public UserResponse findUserByEmail(String emailAddress);
    public UserResponse findUserByID(Integer ID);
    public Boolean userExistsByID(Integer ID);
}
