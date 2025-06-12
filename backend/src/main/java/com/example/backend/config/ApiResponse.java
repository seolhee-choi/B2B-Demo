package com.example.backend.config;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 2374523428697822813L;

    private int code;
    private String message;
    private Object body;
    private ZonedDateTime timestamp;

    protected ApiResponse(Object body) {
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

    public static ApiResponse of(Object body) {
        return new ApiResponse(body);
    }
    public static ApiResponse of(HttpStatus status, String message) {
        return new ApiResponse(status, message);
    }
}
