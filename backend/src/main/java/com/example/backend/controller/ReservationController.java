package com.example.backend.controller;

import com.example.backend.config.ApiResponse;
import com.example.backend.dto.CustomerDto;
import com.example.backend.dto.ReservationDto;
import com.example.backend.service.CustomerService;
import com.example.backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reserv")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;

    @PostMapping("/getReservList")
    public ApiResponse<List<ReservationDto>> getReservationList() {
        try {
            return ApiResponse.of(reservationService.getReservationList());
        }catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/getReservationsByDate")
    public ApiResponse<Map<String, List<Map<String, Object>>>> getReservationsByDate() {
        try {
            return ApiResponse.of(reservationService.getReservationsByDate());
        } catch (Exception e) {
            return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}
