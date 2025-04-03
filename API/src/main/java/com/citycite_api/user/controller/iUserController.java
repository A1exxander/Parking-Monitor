package com.citycite_api.user.controller;

import com.citycite_api.user.dto.UserResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface iUserController {

    @GetMapping("/current")
    public ResponseEntity<UserResponse> getCurrentUser(@NotNull Authentication authentication);

}
