package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.service.iUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class AuthService implements iAuthService {

    @Autowired
    private iCredentialsService credentialsService;

    @Autowired
    private iUserService userService;

    public void registerUser(UserRequest userRequest, CredentialsRequest credentialsRequest) {

        if (credentialsService.emailAddressExists(credentialsRequest.getEmailAddress())) {
            throw new IllegalArgumentException("Invalid registration request! Email already in use."); // TODO : Swap to a custom generic exception ( ResourceAlreadyExists )
        }

        String hashedPassword = credentialsService.hashPassword(credentialsRequest.getPassword());
        userService.createUser(userRequest, credentialsRequest.getEmailAddress(), hashedPassword); // Don't send raw password if we don't need to

    }

}
