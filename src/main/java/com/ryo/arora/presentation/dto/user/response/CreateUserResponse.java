package com.ryo.arora.presentation.dto.user.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate doB;
    private String avatarImg;
}
