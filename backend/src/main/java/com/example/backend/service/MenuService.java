package com.example.backend.service;

import com.example.backend.dto.MenuDto;
import com.example.backend.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;


    public int existUser(String userId) {
        return menuMapper.existUser(userId);
    }



    public List<MenuDto> selectMenuByUser(String userId) {
        return menuMapper.selectMenuByUser(userId);
    }


    public List<MenuDto> selectMenuByNoUser() {
        return menuMapper.selectMenuByNoUser();
    }




}
