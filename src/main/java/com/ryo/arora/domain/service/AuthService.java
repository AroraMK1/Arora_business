package com.ryo.arora.domain.service;

import com.nimbusds.jose.JOSEException;
import com.ryo.arora.infrastructure.persistence.entity.TokenEntity;
import com.ryo.arora.presentation.dto.auth.condition.LoginConditionEntity;

import java.text.ParseException;

public interface AuthService {
    TokenEntity authenticate(LoginConditionEntity user);

    boolean introspect(String token) throws JOSEException, ParseException;

    void logout(String accessToken, String refreshToken);
}
