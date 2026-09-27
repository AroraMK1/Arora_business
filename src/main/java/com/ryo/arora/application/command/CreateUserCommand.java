package com.ryo.arora.application.command;

import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import lombok.*;

import java.time.Instant;

@LogicFunction(BusinessCode.CREATE_USER)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommand {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Instant doB;
}
