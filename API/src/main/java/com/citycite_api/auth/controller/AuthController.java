package com.citycite_api.auth.controller;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.auth.service.iAuthService;
import com.citycite_api.user.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class AuthController implements iAuthController {

    @Autowired
    private iAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestPart("userRequest") UserRequest userRequest,
                                         @Valid @RequestPart("credentialsRequest") CredentialsRequest credentialsRequest,
                                         @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture)
    {
        authService.registerUser(userRequest, credentialsRequest, profilePicture);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

