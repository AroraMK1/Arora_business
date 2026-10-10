package com.ryo.arora.domain.handler.auth;

import com.ryo.arora.domain.handler.core.GenericService;
import com.ryo.arora.domain.mapper.AuthenticationMapper;
import com.ryo.arora.domain.service.AuthService;
import com.ryo.arora.presentation.dto.auth.condition.LoginConditionEntity;
import com.ryo.arora.presentation.dto.auth.response.LoginResponse;
import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import com.ryo.arora.shared.response.ResponseDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@LogicFunction(BusinessCode.LOGIN_USER)
public class LoginHandler implements GenericService<LoginConditionEntity, ResponseDataEntity<LoginResponse>> {

    private final AuthService authService;
    private final AuthenticationMapper authenticationMapper;

    @Override
    public void verify(LoginConditionEntity request) {

    }

    @Override
    public ResponseDataEntity<LoginResponse> execute(LoginConditionEntity request) {
        return ResponseDataEntity.<LoginResponse>builder()
                .message("Authenticate success")
                .result(authenticationMapper.toLoginResponse(authService.authenticate(request)))
                .build();
    }

    @Override
    public void errorHandler(Exception exception) {

    }
}
