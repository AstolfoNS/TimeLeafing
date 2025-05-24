package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@TableName("role")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleEntity {

    // 唯一标识
    @TableId
    private Long id;

    // 基础属性
    private String roleName;

    private String description;

    @TableField(exist = false)
    private List<Long> permissionIdList = List.of();

    // 生命周期
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

}
