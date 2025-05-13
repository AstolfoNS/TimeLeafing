package com.astolfo.domain.rbac.model;

import com.astolfo.domain.rbac.model.valueobject.entity.Symbol;
import com.astolfo.domain.rbac.model.valueobject.enumtype.PermissionPoint;
import com.astolfo.domain.rbac.model.valueobject.enumtype.HttpMethod;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission {

    // 唯一标识
    private Long id;

    // 基础属性
    private Symbol symbol;

    private String description;

    private String url;

    private HttpMethod httpMethod;

    private PermissionPoint point;

    private Integer orderNum;

    // 生命周期属性
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;


    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        setEnabled(true);
    }

    public void softDelete() {
        setIsDeleted(true);
        disable();
    }

    public void restore() {
        setIsDeleted(false);
        enable();
    }
}
