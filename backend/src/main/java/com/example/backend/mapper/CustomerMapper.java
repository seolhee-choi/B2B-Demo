package com.example.backend.mapper;

import com.example.backend.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {
    List<CustomerDto> getCustomerList(String sword);
    int insertCustomer(CustomerDto customerDto);
    int updateCustomer(CustomerDto customerDto);
    int updateCustomerOne(Map map);
}
