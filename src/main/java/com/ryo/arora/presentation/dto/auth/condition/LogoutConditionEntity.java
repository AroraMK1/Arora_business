package com.ryo.arora.presentation.dto.auth.condition;

import com.ryo.arora.shared.annotation.LogicFunction;
import com.ryo.arora.shared.enums.BusinessCode;
import lombok.*;

@LogicFunction(BusinessCode.LOGOUT_USER)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutConditionEntity {
    private String accessToken;
    private String refreshToken;
}
