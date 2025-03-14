package com.citycite_api.auth.controller;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.user.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface iAuthController {

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestPart("userRequest") UserRequest userRequest,
                                         @Valid @RequestPart("credentialsRequest") CredentialsRequest credentialsRequest,
                                         @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture);

}
