package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
private int custId;
private String custNm;
private int age;
private String gender;
private String phone;
private String postCode;
private String addr1;
private String addr2;
private String createdAt;
}
