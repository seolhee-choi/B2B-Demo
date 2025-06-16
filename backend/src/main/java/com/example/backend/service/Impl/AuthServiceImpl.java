package com.example.backend.service.Impl;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.dto.UserDto;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserDto user = userMapper.getUserForLogin(request.getEmail());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다.");
        }
        return new LoginResponse(
                user.getUserId(),
                user.getEmail(),
                user.getRoleCd()
        );
    }
}
