package com.core.SpeakEasy.service;

import com.core.SpeakEasy.model.AuthUser;

public class AuthResponse {
    private AuthUser user;
    private String token;

    public AuthResponse(AuthUser user, String token) {
        this.user = user;
        this.token = token;
    }

    public AuthUser getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
