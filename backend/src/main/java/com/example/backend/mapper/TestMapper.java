package com.example.backend.mapper;

import com.example.backend.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<TestDto> getConnectCheck();
}
