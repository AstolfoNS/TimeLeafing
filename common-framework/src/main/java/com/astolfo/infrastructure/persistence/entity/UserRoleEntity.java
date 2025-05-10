package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("user_role")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoleEntity {

    private Long userId;

    private Long roleId;

    private LocalDateTime createTime;

    private Boolean isDeleted;

}
