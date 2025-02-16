package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.dto.UserResponse;
import com.citycite_api.user.service.iUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@AllArgsConstructor
public class AuthService implements iAuthService {

    @Autowired
    private iCredentialsService credentialsService;

    @Autowired
    private iUserService userService;

    @Autowired
    private AccessJWTService accessJWTService;

    @Override
    public void register(UserRequest userRequest, CredentialsRequest credentialsRequest, MultipartFile profilePicture) {

        if (credentialsService.emailAddressExists(credentialsRequest.getEmailAddress())) {
            throw new IllegalArgumentException("Invalid registration request! Email already in use."); // TODO : Swap to a custom generic exception ( ResourceAlreadyExists )
        }

        String hashedPassword = credentialsService.hashPassword(credentialsRequest.getPassword());
        userService.createUser(userRequest, credentialsRequest.getEmailAddress(), hashedPassword); // Don't send raw password if we don't need to

    }

    @Override
    public String login(CredentialsRequest credentialsRequest) {

        if (!credentialsService.areValidCredentials(credentialsRequest)) {
            throw new IllegalArgumentException("Invalid login request! Invalid credentials provided."); // Don't inform user if their email exists or if credentials are invalid
        }

        UserResponse user = userService.findUserByEmail(credentialsRequest.getEmailAddress());
        return accessJWTService.generateAccessJWT(user.getID(), user.getAccountType());

    }

}
