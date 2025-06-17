package com.example.backend.service;

import com.example.backend.dto.MenuDto;
import com.example.backend.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;


    public int existUser(String userId) {
        return menuMapper.existUser(userId);
    }



    public List<MenuDto> selectMenuByUser(String userId) {
        Map<Integer, MenuDto> menuMap = new HashMap<>();
        List<MenuDto> rootMenus = new ArrayList<>();
        List<MenuDto> menuList =  menuMapper.selectMenuByUser(userId);

        // 1. 먼저 menuId 기준으로 map에 저장
        for (MenuDto menu : menuList) {
            menuMap.put(menu.getMenuId(), menu);
        }

        // 2. 계층 구조로 변환
        for (MenuDto menu : menuList) {
            if (menu.getParentId() == 0) {
                // 최상위 메뉴
                rootMenus.add(menu);
            } else {
                // 부모 메뉴에 children 으로 추가
                MenuDto parent = menuMap.get(menu.getParentId());
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(menu);
                }
            }
        }


        return rootMenus;
    }


    public List<MenuDto> selectMenuByNoUser() {
        return menuMapper.selectMenuByNoUser();
    }




}
