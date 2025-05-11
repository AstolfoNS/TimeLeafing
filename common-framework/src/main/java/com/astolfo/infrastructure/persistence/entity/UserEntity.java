package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @TableId
    private Long id;

    private String email;

    private String username;

    private String password;

    private String avatar;

    private String gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

}
