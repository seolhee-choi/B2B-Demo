package com.example.backend.service;

import com.example.backend.dto.SalesDto;
import com.example.backend.mapper.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesMapper salesMapper;

    public List<SalesDto> selectSalesListAll() {
        return salesMapper.selectSalesListAll();
    }

    public void saveSales(SalesDto data) {
        salesMapper.insertSalesInfo(data);
    }




}
