package com.astolfo.infrastructure.persistence.entity;

<<<<<<< HEAD
public class UserEntity {
=======
import com.astolfo.domain.rbac.model.valueobject.Gender;
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

    private Long id;

    private String username;

    private String password;

    private String email;

    private String avatar;

    private Gender gender;

    private String introduction;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

>>>>>>> main
}
