package com.ryo.arora.presentation.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {
    @NotBlank(message = "Access token must not be blank")
    private String accessToken;
    @NotBlank(message = "Refresh token must not be blank")
    private String refreshToken;
}
