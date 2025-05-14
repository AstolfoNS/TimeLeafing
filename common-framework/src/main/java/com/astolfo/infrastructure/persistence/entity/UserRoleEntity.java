package com.astolfo.infrastructure.persistence.entity;

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

    // 唯一标识
    @TableId
    private Long id;

    // 联合主键
    private Long userId;

    private Long roleId;

    // 生命周期
    private LocalDateTime createTime;

    private Boolean isDeleted;

}
