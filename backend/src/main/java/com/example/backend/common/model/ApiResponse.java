package com.example.backend.common.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

  private String status;        // 응답 상태 (success, error)
     private String message;       // 메시지
     private T body;
     private LocalDateTime timestamp;  // 응답 생성 시간
    private int totalPages;
    private long totalElements;

     public ApiResponse(String status, String message, T body, int totalPages, long totalElements) {
         this.status = status;
         this.message = message;
         this.body = body;
         this.timestamp = LocalDateTime.now();
         this.totalElements = totalElements;
         this.totalPages = totalPages;
     }

  public ApiResponse(String status, String message) {
           this.status = status;
           this.message = message;
           this.timestamp = LocalDateTime.now();
       }

  public ApiResponse(String status, String message, T body) {
             this.status = status;
             this.message = message;
             this.body = body;
             this.timestamp = LocalDateTime.now();
         }


 }
