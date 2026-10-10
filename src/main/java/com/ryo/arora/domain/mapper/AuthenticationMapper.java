package com.ryo.arora.domain.mapper;

import com.ryo.arora.infrastructure.persistence.entity.TokenEntity;
import com.ryo.arora.presentation.dto.auth.response.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    LoginResponse toLoginResponse(TokenEntity tokenEntity);
}
