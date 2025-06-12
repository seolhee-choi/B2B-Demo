package com.example.backend.common.model;

import jakarta.validation.ConstraintViolationException;
import java.util.concurrent.TimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice는 모든 @RestController에서 발생한 예외를 처리합니다.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 특정 예외를 처리하는 메서드
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(Exception e) {
        return new ApiResponse<>("error", e.getMessage(), null);
    }

    // NullPointerException을 처리하는 메서드
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleNullPointerException(NullPointerException e) {
        return new ApiResponse<>("error", "Null value encountered", null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        // 모든 오류 메시지를 한 줄로 모은다.
        for (ObjectError error : result.getAllErrors()) {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        }

        return new ApiResponse<>("error", errorMessage.toString(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ApiResponse<>("error", "Invalid JSON format", null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return new ApiResponse<>("error", "Constraint violation: " + ex.getMessage(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ApiResponse<>("error", "Access Denied", null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<Object> handleAuthenticationException(AuthenticationException ex) {
        return new ApiResponse<>("error", "Authentication failed: " + ex.getMessage(), null);
    }

    @ExceptionHandler(TimeoutException.class)
    public ApiResponse<Object> handleTimeoutException(TimeoutException ex) {
        return new ApiResponse<>("error", "Request timed out: " + ex.getMessage(), null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ApiResponse<>("error", "Invalid argument: " + ex.getMessage(), null);
    }

    // 다른 예외 처리 (예: 특정 커스텀 예외)
    @ExceptionHandler(MyCustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleMyCustomException(MyCustomException e) {
        return new ApiResponse<>("error", e.getMessage(), null);
    }
}
