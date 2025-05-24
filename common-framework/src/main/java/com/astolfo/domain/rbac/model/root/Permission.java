package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.Symbol;
import com.astolfo.domain.rbac.model.valueobject.Point;
import com.astolfo.domain.rbac.model.valueobject.HttpMethod;
import com.astolfo.domain.rbac.model.valueobject.UrlPattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Data
    public static class Details {

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
    }

    public Long getIdLong() {
        if (Objects.isNull(id)) {
            return null;
        } else {
            return id.getPermissionId();
        }
    }

    public String getSymbolString() {
        if (Objects.isNull(symbol)) {
            return null;
        } else {
            return symbol.getSymbol();
        }
    }

    public String getUrlString() {
        if (Objects.isNull(url)) {
            return null;
        } else {
            return url.toString();
        }
    }

    public String getHttpMethodString() {
        if (Objects.isNull(httpMethod)) {
            return null;
        } else {
            return httpMethod.toString();
        }
    }

    public String getPointString() {
        if (Objects.isNull(point)) {
            return null;
        } else {
            return point.toString();
        }
    }

    private Permission(Details details) {
        setId(details.getId());

        setSymbol(details.getSymbol());

        setDescription(details.getDescription());

        setUrl(details.getUrl());

        setHttpMethod(details.getHttpMethod());

        setPoint(details.getPoint());

        setOrderNum(details.getOrderNum());

        setEnabled(details.getEnabled());

        setCreateTime(details.getCreateTime());

        setUpdateTime(details.getUpdateTime());

        setIsDeleted(details.getIsDeleted());
    }

    public static Permission of(Details details) {
        return new Permission(details);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Permission permission = (Permission) o;

        return id.equals(permission.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
