package com.example.backend.mapper;

import com.example.backend.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<CustomerDto> getCustomerList();
}
