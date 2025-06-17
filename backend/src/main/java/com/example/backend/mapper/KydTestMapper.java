package com.example.backend.mapper;

import com.example.backend.dto.KydTestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("KydTestMapper")
public interface KydTestMapper extends JpaRepository<KydTestDto, String> {
}
