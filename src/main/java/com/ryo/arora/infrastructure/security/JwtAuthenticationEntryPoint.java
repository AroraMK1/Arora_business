package com.ryo.arora.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.arora.shared.exception.ErrorCode;
import com.ryo.arora.shared.response.ResponseDataEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Exception: " + authException.getClass().getName());
        System.out.println("Message: " + authException.getMessage());

        authException.printStackTrace();

        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseDataEntity<?> apiResponse = ResponseDataEntity.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.flushBuffer();
    }
}