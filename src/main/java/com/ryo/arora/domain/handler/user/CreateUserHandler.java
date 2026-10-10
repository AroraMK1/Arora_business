package com.ryo.arora.domain.handler.user;

import com.ryo.arora.domain.handler.core.GenericService;
import com.ryo.arora.domain.mapper.UserApplicationMapper;
import com.ryo.arora.domain.service.UserService;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import com.ryo.arora.presentation.dto.user.condition.CreateUserConditionEntity;
import com.ryo.arora.presentation.dto.user.response.CreateUserResponse;
import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import com.ryo.arora.shared.exception.AppException;
import com.ryo.arora.shared.exception.ErrorCode;
import com.ryo.arora.shared.response.ResponseDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@LogicFunction(BusinessCode.CREATE_USER)
public class CreateUserHandler implements GenericService<CreateUserConditionEntity, ResponseDataEntity<CreateUserResponse>> {

    private final UserService userService;
    private final UserApplicationMapper userApplicationMapper;

    @Override
    public void verify(CreateUserConditionEntity request) {

        if (userService.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (userService.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Override
    public ResponseDataEntity<CreateUserResponse> execute(
            CreateUserConditionEntity request
    ) {
        UserEntity response = userService.save(
                userApplicationMapper.toUserDomainEntity(request)
        );
        return ResponseDataEntity.<CreateUserResponse>builder()
                .message("User created successfully")
                .result(userApplicationMapper.toCreateUserResponse(response))
                .build();
    }

    @Override
    public void errorHandler(Exception exception) {
        // ...
    }
}
