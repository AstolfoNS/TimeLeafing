package com.astolfo.webinterface.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {

    private Long id;

    private String email;

    private String username;

    private String avatar;

    private String gender;

    private String introduction;

    private LocalDateTime lastLoginTime;
}
