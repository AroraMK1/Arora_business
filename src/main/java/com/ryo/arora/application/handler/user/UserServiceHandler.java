package com.ryo.arora.application.handler.user;

import com.ryo.arora.application.handler.GenericService;
import com.ryo.arora.domain.service.UserService;
import com.ryo.arora.application.command.CreateUserCommand;
import com.ryo.arora.application.dto.response.CreateUserResponse;
import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import com.ryo.arora.shared.response.ResponseDataEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@LogicFunction(BusinessCode.CREATE_USER)
public class UserServiceHandler implements GenericService<CreateUserCommand, ResponseDataEntity<CreateUserResponse>> {

    UserService userService;

    @Override
    public void verify(CreateUserCommand request) {

    }

    @Override
    public ResponseDataEntity<CreateUserResponse> execute(CreateUserCommand request) {
        return ResponseDataEntity.<CreateUserResponse>builder()
                .message("Workflow ok")
                .build();
    }

    @Override
    public void errorHandler(Exception exception) {

    }
}
