package com.astolfo.webinterface.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    private Long id;

    private String username;

    private String email;

    private String avatar;

    private String gender;

    private String introduction;

}
