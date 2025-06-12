package com.example.backend.service;

import com.example.backend.dto.TestDto;
import com.example.backend.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    public List<TestDto> getConnectCheck() {
        return testMapper.getConnectCheck();
    }
}
