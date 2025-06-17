package com.example.backend.mapper;

import com.example.backend.dto.KydDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository("kydMapper")
public interface KydMapper{
    List<KydDto> find_1(HashMap<String, Object> params);

    int find_2();
    List<Map<String, Object>> find_3(Pageable pageable);
}
