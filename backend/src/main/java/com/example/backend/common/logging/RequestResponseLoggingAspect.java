package com.example.backend.common.logging;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestResponseLoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);

      // 모든 RestController에 대해 요청과 응답을 로깅할 수 있는 포인트컷 설정
      @Pointcut("execution(* com.example.backend.controller.*.*(..)) && @within(org.springframework.web.bind.annotation.RestController)")
      public void restControllerMethods() {}

      // 요청 전에 로그를 남김
      @Before("restControllerMethods()")
      public void logRequestDetails() {
          logger.info("Request received at: {}", System.currentTimeMillis());
          // 요청의 세부 정보를 추가로 기록할 수 있습니다. 예: URL, 요청 파라미터 등
      }

      // 응답이 정상적으로 반환된 후 로그를 남김
      @AfterReturning(value = "restControllerMethods()", returning = "result")
      public void logResponseDetails(Object result) {
          logger.info("Response sent at: {}", System.currentTimeMillis());
          logger.info("Response body: {}", result);
      }

      // 예외 발생 시 로그를 남김
      @AfterThrowing(value = "restControllerMethods()", throwing = "exception")
      public void logExceptionDetails(Exception exception) {
          logger.error("Exception occurred at: {}", System.currentTimeMillis());
          logger.error("Exception message: {}", exception.getMessage());
      }
}
