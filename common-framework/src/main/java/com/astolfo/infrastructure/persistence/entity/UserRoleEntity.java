package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;

    private LocalDateTime createTime;

    private Boolean isDeleted;

}
