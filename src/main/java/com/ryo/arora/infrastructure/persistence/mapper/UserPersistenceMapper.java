package com.ryo.arora.infrastructure.persistence.mapper;

import com.ryo.arora.domain.model.user.UserModel;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toEntity(UserModel user);

    UserModel toDomain(UserEntity entity);
}