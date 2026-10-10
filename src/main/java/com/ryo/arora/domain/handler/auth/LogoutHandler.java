package com.ryo.arora.domain.handler.auth;

import com.ryo.arora.domain.handler.core.GenericService;
import com.ryo.arora.domain.service.AuthService;
import com.ryo.arora.presentation.dto.auth.condition.LoginConditionEntity;
import com.ryo.arora.presentation.dto.auth.condition.LogoutConditionEntity;
import com.ryo.arora.presentation.dto.auth.response.LoginResponse;
import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import com.ryo.arora.shared.response.ResponseDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@LogicFunction(BusinessCode.LOGOUT_USER)
public class LogoutHandler implements GenericService<LogoutConditionEntity, ResponseDataEntity<?>> {
    private final AuthService authService;

    @Override
    public void verify(LogoutConditionEntity request) {

    }

    @Override
    public ResponseDataEntity<?> execute(LogoutConditionEntity request) {
        authService.logout(request.getAccessToken(), request.getRefreshToken());
        return ResponseDataEntity.builder()
                .message("Logout success")
                .build();

    }

    @Override
    public void errorHandler(Exception exception) {

    }
}
