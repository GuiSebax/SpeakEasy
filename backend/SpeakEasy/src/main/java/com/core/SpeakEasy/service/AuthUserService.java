package com.core.SpeakEasy.service;

import com.core.SpeakEasy.exceptions.NotFoundException;
import com.core.SpeakEasy.exceptions.ValidationException;
import com.core.SpeakEasy.model.AuthUser;
import com.core.SpeakEasy.model.Role;
import com.core.SpeakEasy.repository.AuthUserRepository;
import com.core.SpeakEasy.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse registerUser(AuthUser user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ValidationException("Email e senha são obrigatórios.");
        }

        // Verifica se o email já está cadastrado
        Optional<AuthUser> existingUser = authUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ValidationException("Email já cadastrado.");
        }

        // Criptografa a senha antes de salvar
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        Role userRole = user.getRole() != null && user.getRole() == Role.ADMIN ? Role.ADMIN : Role.USER;

        AuthUser newUser = new AuthUser();
        newUser.setEmail(user.getEmail().trim().toLowerCase());
        newUser.setPassword(hashedPassword);
        newUser.setName(user.getName() != null ? user.getName().trim() : null);
        newUser.setRole(userRole);
        authUserRepository.save(newUser);

        String token = jwtUtil.generateToken(newUser.getId());

        return new AuthResponse(newUser, token);

    }

    public AuthResponse loginUser(AuthUser user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new ValidationException("Email e senha são obrigatórios");
        }

        // Busca usuário pelo email
        AuthUser authUser = authUserRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // Verifica a senha
        if (!passwordEncoder.matches(user.getPassword(), authUser.getPassword())) {
            throw new ValidationException("Senha inválida");
        }

        // Gera o token JWT
        String token = jwtUtil.generateToken(authUser.getId());

        return new AuthResponse(authUser, token);
    }

}
