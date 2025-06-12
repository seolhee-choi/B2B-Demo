package com.example.backend.user.service;

import com.example.backend.user.dto.LoginRequest;
import com.example.backend.user.dto.LoginResponse;
import com.example.backend.user.model.UserWithRole;

import com.example.backend.user.repository.AuthDao;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthDao repository;

    public AuthService(AuthDao repository) {
        this.repository = repository;
    }

    public UserWithRole login(String email, String password) {
        UserWithRole user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호 불일치");
        }

        return user;
    }
}
