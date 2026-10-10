package com.ryo.arora.domain.mapper;

import com.ryo.arora.domain.entity.UserDomainEntity;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import com.ryo.arora.presentation.dto.user.condition.CreateUserConditionEntity;
import com.ryo.arora.presentation.dto.user.response.CreateUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserApplicationMapper {

    CreateUserResponse toCreateUserResponse(UserEntity user);

    UserDomainEntity toUserDomainEntity(CreateUserConditionEntity condition);

}