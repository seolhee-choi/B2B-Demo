package com.example.backend.config;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 2374523428697822813L;

    private int code;
    private String message;
    private T body; // 제네릭 타입 사용
    private ZonedDateTime timestamp;

    protected ApiResponse(T body) {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.body = body;
        this.timestamp = ZonedDateTime.now();
    }

    protected ApiResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }

    public static <T> ApiResponse<T> of(T body) {
        return new ApiResponse<>(body);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse<>(status, message);
        response.setBody(null);
        return response;

    }
}

