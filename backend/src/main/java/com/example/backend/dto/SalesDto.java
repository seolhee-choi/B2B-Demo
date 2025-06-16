package com.example.backend.dto;

import lombok.Data;

@Data
public class SalesDto {

    private String salesId;
    private String custId;
    private String userId;
    private String proId;
    private String deliveryDate;

    // TODO List<Pro> 로 받을 수 있도록?
    private String proCd;
    private String proNm;
    private String price;
    private String disPrice;
    private String finPrice;

    private String custNm;
    private String address;

    private String email;
}
