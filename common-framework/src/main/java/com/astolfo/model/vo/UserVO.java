package com.astolfo.model.vo;

import com.astolfo.common.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO {

    private String username;

    private String avatar;

    private Gender gender;

    private String introduction;

    private Date lastLoginTime;

}
