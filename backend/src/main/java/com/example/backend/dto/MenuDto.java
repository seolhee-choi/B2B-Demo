package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class MenuDto {

    @Schema(description = "메뉴 ID")
    private int menuId;

    @Schema(description = "메뉴명")
    private String menuNm;

    @Schema(description = "URL")
    private String pageUrl;

    @Schema(description = "상위메뉴 ID")
    private int parentId;

    @Schema(description = "메뉴 계층")
    private int depth;


    private String first;
    private String second;
    private String third;
    private String comPath;


    private List<MenuDto> children;

}
