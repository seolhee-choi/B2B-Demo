package com.example.backend.user.controller;

import com.example.backend.user.dto.LoginRequest;
import com.example.backend.user.dto.LoginResponse;
import com.example.backend.user.model.UserWithRole;
import com.example.backend.user.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
        UserWithRole user = authService.login(request.getEmail(), request.getPassword());
        session.setAttribute("USER_ID", user.getUserId());
        session.setAttribute("ROLE_CD", user.getRoleCd());

        LoginResponse response = new LoginResponse(
                user.getUserId(),
                user.getEmail(),
                user.getRoleCd()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }
}
