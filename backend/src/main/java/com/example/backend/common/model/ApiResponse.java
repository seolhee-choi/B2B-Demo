package com.example.backend.common.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

  private String status;        // 응답 상태 (success, error)
     private String message;       // 메시지
     private T data;               // 실제 데이터
     private LocalDateTime timestamp;  // 응답 생성 시간

     public ApiResponse(String status, String message, T data) {
         this.status = status;
         this.message = message;
         this.data = data;
         this.timestamp = LocalDateTime.now();
     }


 }
