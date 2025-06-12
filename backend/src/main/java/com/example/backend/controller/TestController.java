package com.example.backend.controller;

import com.example.backend.dto.TestDto;
import com.example.backend.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/conn")
    public ResponseEntity<List<TestDto>> getConnectCheck() {
        return ResponseEntity.ok().body(testService.getConnectCheck());
    }

}
