package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("role_menu")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleMenuEntity {

    @TableId
    private Long id;

    private Long roleId;

    private Long menuId;

    private LocalDateTime createTime;

    private Boolean isDeleted;

}