package com.example.backend.mapper;

import com.example.backend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDto getUserForLogin(@Param("email") String email);
}
