package com.example.backend.service;


import com.example.backend.dto.KydDto;
import com.example.backend.dto.KydTestDto;
import com.example.backend.mapper.KydMapper;
import com.example.backend.mapper.KydTestMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KydService {

    @Resource(name = "kydMapper")
    private KydMapper kydMapper;

    @Resource(name = "KydTestMapper")
    private KydTestMapper kydTestMapper;

    public List<KydDto> find_1(HashMap<String, Object> params) {
        return kydMapper.find_1(params);
    }
    public Page<Map<String, Object>> find_2(String keyword, Pageable pageable) {
        List<Map<String, Object>> content = kydMapper.find_3(pageable);
        int total = kydMapper.find_2();
        return new PageImpl<>(content, pageable, total);
    }
    public Page<KydTestDto> find_3(String keyword, Pageable pageable) {
        return kydTestMapper.findAll(pageable);
    }
    public List<Map<String, Object>> find_4(Pageable pageable) {
        List<Map<String, Object>> content = kydMapper.find_3(pageable);
        return content;
    }
}
