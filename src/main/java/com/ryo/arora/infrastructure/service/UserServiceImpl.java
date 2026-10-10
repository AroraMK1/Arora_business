package com.ryo.arora.infrastructure.service;

import com.ryo.arora.domain.entity.UserDomainEntity;
import com.ryo.arora.domain.service.UserService;
import com.ryo.arora.infrastructure.persistence.entity.UserEntity;
import com.ryo.arora.infrastructure.persistence.mapper.UserPersistenceMapper;
import com.ryo.arora.infrastructure.persistence.repository.jpa.UserJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity save(UserDomainEntity user) {
        UserEntity userEntity = userPersistenceMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(userEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
