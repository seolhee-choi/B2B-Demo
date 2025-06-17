package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
private int reservId;
private String custNm;
    private String type;
    private String interestPd; // 관심 제품들
    private String reservDate;
    private String reservTime;
}
