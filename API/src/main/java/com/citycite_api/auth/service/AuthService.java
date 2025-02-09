package com.citycite_api.auth.service;

import com.citycite_api.auth.dto.RegistrationRequest;
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

    public void registerUser(RegistrationRequest registrationRequest) {

        if (credentialsService.emailAddressExists(registrationRequest.getCredentialsRequest().getEmailAddress())) {
            throw new IllegalArgumentException("Invalid registration request! Email already in use."); // TODO : Swap to a custom generic exception ( ResourceAlreadyExists )
        }

        String hashedPassword = credentialsService.hashPassword(registrationRequest.getCredentialsRequest().getPassword());
        userService.createUser(registrationRequest.getUserRequest(), registrationRequest.getCredentialsRequest().getEmailAddress(), hashedPassword); // Don't send raw password if we don't need to

    }

}
