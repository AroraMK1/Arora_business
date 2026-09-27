package com.ryo.arora.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    private Long userId;

    private String username;
    private String firstName;
    private String lastName;
}
