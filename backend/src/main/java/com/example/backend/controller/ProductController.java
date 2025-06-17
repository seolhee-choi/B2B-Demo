package com.example.backend.controller;

import com.example.backend.config.ApiResponse;
import com.example.backend.dto.ProductDto;
import com.example.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product api", description = "상품조회 api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    @Operation(summary = "제품명 검색", description = "입력한 제품명으로 제품목록 조회")
    public ApiResponse<List<ProductDto>> getProductsByName(
            @Parameter(description = "검색할 제품명", example = "냉장고")
            @RequestParam(required = false) String productName) {
        List<ProductDto> products = productService.getProductsByName(productName);
        if (products.isEmpty()) {
            return ApiResponse.of(HttpStatus.NOT_FOUND, "조회된 제품이 없습니다.");
        }
        return ApiResponse.of(products);
    }
}
