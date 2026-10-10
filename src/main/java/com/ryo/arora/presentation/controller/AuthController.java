package com.ryo.arora.presentation.controller;

import com.ryo.arora.domain.handler.core.ServiceDispatcher;
import com.ryo.arora.presentation.dto.auth.condition.LoginConditionEntity;
import com.ryo.arora.presentation.dto.auth.condition.LogoutConditionEntity;
import com.ryo.arora.presentation.dto.auth.request.LoginRequest;
import com.ryo.arora.presentation.dto.auth.request.LogoutRequest;
import com.ryo.arora.presentation.dto.auth.response.LoginResponse;
import com.ryo.arora.shared.response.ResponseDataEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ServiceDispatcher serviceDispatcher;

    @PostMapping("/login")
    public ResponseDataEntity<LoginResponse> createUser(
            @Valid @RequestBody LoginRequest request
    ) {
        return this.serviceDispatcher.execute(
                LoginConditionEntity.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseDataEntity<?> logoutUser(
            @Valid @RequestBody LogoutRequest request
    ){
        return this.serviceDispatcher.execute(
                LogoutConditionEntity.builder()
                        .accessToken(request.getAccessToken())
                        .refreshToken(request.getRefreshToken())
                        .build()
        );
    }
}
