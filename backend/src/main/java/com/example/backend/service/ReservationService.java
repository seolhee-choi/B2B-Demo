package com.example.backend.service;

import com.example.backend.dto.CustomerDto;
import com.example.backend.dto.ReservationDto;
import com.example.backend.mapper.CustomerMapper;
import com.example.backend.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getReservationList() {
        return reservationMapper.getReservationList();
    }

    public Map<String, List<Map<String, Object>>> getReservationsByDate() {
        List<ReservationDto> rawList = reservationMapper.getReservationsByDate();
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (ReservationDto dto : rawList) {
//            String date = dto.getCreatedAt().format(dateFormatter);
//            String time = dto.getCreatedAt().format(timeFormatter);

            result.computeIfAbsent(dto.getReservDate().toString(), k -> new ArrayList<>())
                    .add(Map.of(
                            "time", dto.getReservTime(),
                            "cust_nm", dto.getCustNm(),
                            "type", dto.getType(),
                            "interest_pd", Arrays.asList(dto.getInterestPd().split(","))
                    ));
        }

        return result;
    }
}
