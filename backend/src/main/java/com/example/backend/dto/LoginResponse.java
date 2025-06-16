package com.example.backend.dto;

public class LoginResponse {
    private Long userId;
    private String email;
    private String roleCd;

    public LoginResponse(Long userId, String email, String roleCd) {
        this.userId = userId;
        this.email = email;
        this.roleCd = roleCd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
}
