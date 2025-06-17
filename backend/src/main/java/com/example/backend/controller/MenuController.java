package com.example.backend.controller;

import com.example.backend.common.model.ApiResponse;
import com.example.backend.dto.MenuDto;
import com.example.backend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/list")
    public ApiResponse<List<MenuDto>> getMenusByUser(@RequestParam String userId) {
        try {
            List<MenuDto> list = menuService.existUser(userId) > 0 ?
                    menuService.selectMenuByUser(userId)
                    : menuService.selectMenuByNoUser();




            return new ApiResponse<>(HttpStatus.OK.toString(), "메뉴조회 성공",list);
        } catch (Exception e) {

            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }

    }



}
