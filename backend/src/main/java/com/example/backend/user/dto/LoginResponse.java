package com.example.backend.user.dto;


public class LoginResponse {
    private int  userId;
    private String email;
    private String roleCd;

    public LoginResponse(int userId, String email, String roleCd) {
        this.userId = userId;
        this.email = email;
        this.roleCd = roleCd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
