package com.example.backend.controller;

import com.example.backend.common.model.ApiResponse;
import com.example.backend.dto.SalesDto;
import com.example.backend.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping("/list")
    public ApiResponse<List<SalesDto>> getSalesList() {

        try {
            List<SalesDto> list = salesService.selectSalesListAll();
            return new ApiResponse<>(HttpStatus.OK.toString(), "상품조회 성공",list);
        } catch (Exception e) {

            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }

    }

    @PostMapping("/add")
    public ApiResponse<String> addSales(@RequestBody SalesDto data) {
        try {
            // 성공후 salesId 필요 X 응답 코드만 전송
            if (data.getUserId() == null) {
                throw new IllegalArgumentException("담당자(User)ID는 null일 수 없습니다.");
            }

            if (data.getProId() == null) {
                throw new IllegalArgumentException("상품ID는 null일 수 없습니다.");
            }

            if (data.getCustId() == null) {
                throw new IllegalArgumentException("고객ID는 null일 수 없습니다.");
            }


            salesService.saveSales(data);
            return new ApiResponse<>(HttpStatus.OK.toString(), "등록 성공", null);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }



}
