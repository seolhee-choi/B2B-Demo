package com.example.backend.dto;

public class LoginResponse {
    private Long userId;
    private long roleId;
    /* roleId : 권한
        1 : 관리자(전체)
        2 : 게스트
        3 : 판매원
        4 : 고객
     */
    public LoginResponse(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
