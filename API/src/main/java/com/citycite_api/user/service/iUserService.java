package com.citycite_api.user.service;

import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.dto.UserResponse;
import com.citycite_api.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iUserService {
    public void createUser(UserRequest userRequest, String emailAddress, String hashedPassword);
    public UserResponse getUserByEmail(String emailAddress);
    public User getUserEntityReferenceByID(Integer ID);
    public UserResponse getUserByID(Integer ID);
    public Boolean userExistsByID(Integer ID);
}
