package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.Symbol;
import com.astolfo.domain.rbac.model.valueobject.Point;
import com.astolfo.domain.rbac.model.valueobject.HttpMethod;
import com.astolfo.domain.rbac.model.valueobject.UrlPattern;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission {

    // 唯一标识
    private PermissionId id;

    // 基础属性
    private Symbol symbol;

    private String description;

    private UrlPattern url;

    private HttpMethod httpMethod;

    private Point point;

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
