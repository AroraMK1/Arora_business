package com.ryo.arora.presentation.controller;

import com.ryo.arora.application.dto.request.CreateUserRequest;
import com.ryo.arora.application.handler.ServiceDispatcher;
import com.ryo.arora.application.command.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController{

    private final ServiceDispatcher serviceDispatcher;

    @PostMapping
    public Object createUser(
            @RequestBody CreateUserRequest request
    ) {
        return serviceDispatcher.execute(CreateUserCommand
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
