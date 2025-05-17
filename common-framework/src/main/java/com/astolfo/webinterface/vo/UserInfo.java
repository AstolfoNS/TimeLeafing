package com.astolfo.webinterface.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {

    private Long id;

    private String email;

    private String username;

    private String nickname;

    private String avatar;

    private String gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

}
