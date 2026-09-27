package com.ryo.arora.infrastructure.persistence.repository;

import com.ryo.arora.domain.model.user.UserModel;
import com.ryo.arora.domain.repository.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserModel save(UserModel user) {
        return null;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return Optional.empty();
    }

}
