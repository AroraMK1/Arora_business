package com.ryo.arora.presentation.dto.user.condition;

import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import lombok.*;

import java.time.LocalDate;

@LogicFunction(BusinessCode.CREATE_USER)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserConditionEntity {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate doB;
}
