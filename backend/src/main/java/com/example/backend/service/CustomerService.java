package com.example.backend.service;

import com.example.backend.dto.CustomerDto;
import com.example.backend.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getCustomerList() {
        return customerMapper.getCustomerList();
    }
}
