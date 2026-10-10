package com.ryo.arora.domain.service;

import com.ryo.arora.domain.entity.UserDomainEntity;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;

public interface UserService {

    UserEntity save(UserDomainEntity user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}