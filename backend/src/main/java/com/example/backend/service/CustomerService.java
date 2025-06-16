package com.example.backend.service;

import com.example.backend.dto.CustomerDto;
import com.example.backend.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getCustomerList(String sword) {
        return customerMapper.getCustomerList(sword);
    }

    public int insertCustomer(CustomerDto customerDto) {
        return customerMapper.insertCustomer(customerDto);
    }

    public int updateCustomer(CustomerDto customerDto) {
        return customerMapper.updateCustomer(customerDto);
    }

    public int updateCustomerOne(Map map) {
        return customerMapper.updateCustomerOne(map);
    }
}
