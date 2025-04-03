package com.core.SpeakEasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.SpeakEasy.exceptions.ValidationException;
import com.core.SpeakEasy.model.AuthUser;
import com.core.SpeakEasy.service.AuthResponse;
import com.core.SpeakEasy.service.AuthUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthUser user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
            }

            AuthResponse newUser = authUserService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthUser user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                return ResponseEntity.badRequest().body("Email e senha são obrigatórios.");
            }

            return ResponseEntity.ok(authUserService.loginUser(user));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao fazer login: " + e.getMessage());
        }
    }
}
