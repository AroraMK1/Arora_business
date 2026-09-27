package com.ryo.arora.domain.repository;

import com.ryo.arora.domain.model.user.UserModel;

import java.util.Optional;

public interface UserRepository {
    UserModel save(UserModel user);
    Optional<UserModel> findById(Long id);
}
