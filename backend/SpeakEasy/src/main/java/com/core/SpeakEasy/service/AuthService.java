package com.core.SpeakEasy.service;

import com.core.SpeakEasy.exceptions.NotFoundException;
import com.core.SpeakEasy.exceptions.ValidationException;
import com.core.SpeakEasy.model.Role;
import com.core.SpeakEasy.model.User;
import com.core.SpeakEasy.repository.UserRepository;
import com.core.SpeakEasy.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse registerUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ValidationException("Email e senha são obrigatórios.");
        }

        // verify if the email is already registered
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ValidationException("Email já cadastrado.");
        }

        // encode the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        // verify user role
        Role userRole = user.getRole() != null && user.getRole() == Role.ADMIN ? Role.ADMIN : Role.USER;

        // create a new user
        User newUser = new User();
        newUser.setEmail(user.getEmail().trim().toLowerCase());
        newUser.setPassword(hashedPassword);
        newUser.setName(user.getName() != null ? user.getName().trim() : null);
        newUser.setRole(userRole);
        newUser.setLevel(0);
        newUser.setXp(0);
        newUser.setCreatedAt(LocalDateTime.now());
        userRepository.save(newUser);

        // Generate the JWT token
        String token = jwtUtil.generateToken(newUser.getId());

        return new AuthResponse(newUser, token);
    }

    public AuthResponse loginUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ValidationException("Email e senha são obrigatórios");
        }

        // fetch user by email
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // verify password
        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new ValidationException("Senha inválida");
        }

        // Generate the JWT token
        String token = jwtUtil.generateToken(foundUser.getId());

        return new AuthResponse(foundUser, token);
    }

}
