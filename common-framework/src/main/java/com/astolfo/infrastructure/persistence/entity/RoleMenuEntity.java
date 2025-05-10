package com.astolfo.infrastructure.persistence.entity;

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

    private Long roleId;

    private Long menuId;

    private LocalDateTime createTime;

    private Boolean isDeleted;

}