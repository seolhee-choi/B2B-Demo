package com.example.backend.mapper;

import com.example.backend.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDto> getReservationList();

    List<ReservationDto> getReservationsByDate(); // 특정 날짜 예약 조회
}
