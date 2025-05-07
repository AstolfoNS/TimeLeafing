package com.astolfo.model.entity;

import com.astolfo.common.enums.Gender;
import com.astolfo.common.enums.UserRole;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @TableId
    private Long id;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private Gender gender;

    private String introduction;

    private Boolean enabled;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private Boolean isDeleted;

}
