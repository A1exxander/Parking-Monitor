package com.citycite_api.user.service;

import com.citycite_api.auth.entity.Credentials;
import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.dto.UserResponse;
import com.citycite_api.user.entity.User;
import com.citycite_api.user.mapper.iUserMapper;
import com.citycite_api.user.repository.iUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UserService implements iUserService {

    @Autowired
    public iUserRepository userRepository;

    @Autowired
    public iUserMapper userMapper;

    @Override
    public void createUser(UserRequest userRequest, String emailAddress, String hashedPassword) {

        User user = userMapper.userRequestToUser(userRequest);
        Credentials userCredentials = new Credentials(emailAddress, hashedPassword);

        user.setCredentials(userCredentials);
        userCredentials.setUser(user);
        // TODO: Setup S3 then upload the file for profile picture from userRequest, get link, and then set it under user

        userRepository.save(user);

    }

    @Override
    public UserResponse findUserByEmail(String emailAddress) {
        User user = userRepository.findByCredentialsEmailAddress(emailAddress).orElseThrow(() -> new NoSuchElementException("User with the email " + emailAddress + " not found."));
        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse findUserByID(Integer ID) {
        User user = userRepository.findById(ID).orElseThrow(() -> new NoSuchElementException("User with ID " + ID + " not found."));
        return userMapper.userToUserResponse(user);
    }

    @Override
    public Boolean userExistsByID(Integer ID) {
        return userRepository.existsById(ID);
    }

}
