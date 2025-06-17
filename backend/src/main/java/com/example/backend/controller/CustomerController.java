package com.example.backend.controller;

import com.example.backend.config.ApiResponse;
import com.example.backend.dto.CustomerDto;
import com.example.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    // 리스트 조회
    @PostMapping("/list")
    public ApiResponse<List<CustomerDto>> getCustomerList(@RequestBody Map<String, Object> keyword) {

        String sword = keyword.get("sword").toString();
        try {
            List<CustomerDto> list = customerService.getCustomerList(sword);

            if(!list.isEmpty()) {
                return ApiResponse.of(list);
            } else {
                return ApiResponse.of(HttpStatus.NO_CONTENT, "고객 정보가 없습니다.");
            }

        }catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    // 고객 insert
    @PostMapping("/save")
    public ApiResponse<CustomerDto> insertCustomer(@RequestBody CustomerDto customerDto) {

        try {
            int result = customerService.insertCustomer(customerDto);

            if (result > 0 ) {
                return ApiResponse.of(customerDto);
            } else {
                return ApiResponse.of(HttpStatus.NOT_FOUND, "등록에 실패하였습니다.");
            }

        }catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    // 고객 정보 전체 수정
    @PostMapping("/update")
    public ApiResponse<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        try {
            int result = customerService.updateCustomer(customerDto);

            if(result > 0) {
                return ApiResponse.of(customerDto);
            } else {
                return ApiResponse.of(HttpStatus.NOT_FOUND, "수정에 실패하였습니다.");
            }

        } catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/updateOne")
    public ApiResponse<Map<String, Object>> updateCustomerOne(@RequestParam int custId, @RequestParam String field, @RequestParam String value) {

        Map<String, Object> map = new HashMap<>();

        map.put("field", field);
        map.put("value", value);
        map.put("custId", custId);

        try {
            int result = customerService.updateCustomerOne(map);

            if(result > 0) {
                return ApiResponse.of(map);
            } else {
                return ApiResponse.of(HttpStatus.NOT_FOUND, "수정에 실패하였습니다.");
            }

        } catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
