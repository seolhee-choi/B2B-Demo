package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class TestDto {

    @Schema(description = "이름")
    private String name;

    @Schema(description = "접속가능여부", example = "O")
    private String conn;
}
