package com.example.backend.mapper;

import com.example.backend.dto.MenuDto;
import com.example.backend.dto.SalesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesMapper {

    List<SalesDto> selectSalesListAll();

    void insertSalesInfo(SalesDto data);


}
