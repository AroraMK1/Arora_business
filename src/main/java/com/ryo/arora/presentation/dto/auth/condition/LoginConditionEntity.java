package com.ryo.arora.presentation.dto.auth.condition;

import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import lombok.*;

@LogicFunction(BusinessCode.LOGIN_USER)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginConditionEntity {
    private String password;
    private String email;
}
