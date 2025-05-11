package com.astolfo.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("enum")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuEntity {

    @TableId
    private Long id;

    private String permission;

    private String description;

    private String url;

    private String requestMethod;

    private String authorityType;

    private Integer orderNum;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

}
