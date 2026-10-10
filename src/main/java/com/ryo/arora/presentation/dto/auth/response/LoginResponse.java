package com.ryo.arora.presentation.dto.auth.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
