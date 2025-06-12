package com.example.backend.controller;

import com.example.backend.config.ApiResponse;
import com.example.backend.dto.CustomerDto;
import com.example.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    @GetMapping("/getCustomerList")
    public ApiResponse<List<CustomerDto>> getCustomerList() {
        try {
            return ApiResponse.of(customerService.getCustomerList());
        }catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
