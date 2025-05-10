package com.astolfo.infrastructure.persistence.entity;

import com.astolfo.domain.rbac.model.valueobject.HttpMethod;
import com.astolfo.domain.rbac.model.valueobject.AuthorityType;
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

    private Long id;

    private String permission;

    private String description;

    private String url;

    private HttpMethod requestMethod;

    private AuthorityType type;

    private Integer orderNum;

    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

}
