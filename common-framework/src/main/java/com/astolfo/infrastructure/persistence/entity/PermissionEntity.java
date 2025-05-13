package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("permission")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionEntity {

    // 唯一标识
    @TableId
    private Long id;

    // 基础属性
    private String symbol;

    private String description;

    private String url;

    private String httpMethod;

    private String point;

    private Integer orderNum;

    // 生命周期
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

}
