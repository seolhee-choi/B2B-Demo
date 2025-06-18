package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @RequiredArgsConstructor
public class ProductDto {
    @Schema(description = "상품ID", example = "1")
    private int proId;
    @Schema(description = "상품코드", example = "RF85DB9581A-6685")
    private String proCd;
    @Schema(description = "상품명", example = "스마트냉장고Y")
    private String proNm;
    @Schema(description = "카테고리", example = "냉장고")
    private String category;
    @Schema(description = "재고", example = "60")
    private int stock;
    @Schema(description = "판매단가", example = "1000000")
    private int price;
    @Schema(description = "할인금액", example = "100000")
    private int disPrice;
    @Schema(description = "판매금액", example = "900000")
    private int finPrice;
    @Schema(description = "등록일", example = "2025-06-02")
    private LocalDateTime createDate;
}
