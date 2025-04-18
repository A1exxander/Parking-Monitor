package com.citycite_api.user.controller;

import com.citycite_api.user.dto.UserResponse;
import com.citycite_api.user.service.iUserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/users")
@Validated
@AllArgsConstructor
public class UserController {

    @Autowired
    private iUserService userService;

    @GetMapping("/current")
    public ResponseEntity<UserResponse> getCurrentUser(@NotNull Authentication authentication) {
        Integer currentUserID = (Integer) authentication.getPrincipal(); // UserID is already validated from our filter
        return ResponseEntity.ok(userService.getUserByID(currentUserID));
    }

}
