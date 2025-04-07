package com.core.SpeakEasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.core.SpeakEasy.model.User;
import com.core.SpeakEasy.service.AuthResponse;
import com.core.SpeakEasy.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody User user) {
        AuthResponse response = authService.loginUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) {
        AuthResponse newUser = authService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }
}
