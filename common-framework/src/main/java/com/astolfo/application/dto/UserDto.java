package com.astolfo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String avatar;

    private String gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

}
