package com.ryo.arora.presentation.controller;

import com.ryo.arora.presentation.dto.user.request.CreateUserRequest;
import com.ryo.arora.domain.handler.core.ServiceDispatcher;
import com.ryo.arora.presentation.dto.user.condition.CreateUserConditionEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController{

    private final ServiceDispatcher serviceDispatcher;

    @PostMapping("/register")
    public Object createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return this.serviceDispatcher.execute(CreateUserConditionEntity
                .builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .doB(request.getDoB())
                .build());
    }

}
