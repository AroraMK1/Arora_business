package com.ryo.arora.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDomainEntity {
    private Long userId;
    private String username;
    private Instant createdAt;

    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate doB;
    private String avatarImg;
}
