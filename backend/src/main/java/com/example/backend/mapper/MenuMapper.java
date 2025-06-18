package com.example.backend.mapper;

import com.example.backend.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDto>selectMenuByUser(String userId);
    List<MenuDto>selectMenuByUser2(String userId);
    List<MenuDto> selectMenuByNoUser();

    int existUser(String userId);
}
