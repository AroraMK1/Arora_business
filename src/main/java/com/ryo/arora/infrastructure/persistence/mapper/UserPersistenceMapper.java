package com.ryo.arora.infrastructure.persistence.mapper;

import com.ryo.arora.domain.entity.UserDomainEntity;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toEntity(UserDomainEntity model);

}