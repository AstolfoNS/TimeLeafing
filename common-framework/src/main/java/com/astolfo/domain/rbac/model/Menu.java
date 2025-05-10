package com.astolfo.domain.rbac.model;

import com.astolfo.domain.rbac.model.valueobject.AuthorityType;
import com.astolfo.domain.rbac.model.valueobject.HttpMethod;
import com.astolfo.domain.rbac.model.valueobject.Permission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Menu {

    @Setter
    private Long id;

    private Permission permission;

    @Setter
    private String description;

    @Setter
    private String url;

    @Setter
    private HttpMethod httpMethod;

    @Setter
    private AuthorityType type;

    @Setter
    private int orderNum;

    private boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean isDeleted;


    public Menu(
            Permission permission,
            String description,
            String url,
            HttpMethod httpMethod,
            AuthorityType type,
            int orderNum
    ) {
        this.id = null;

        this.permission = permission;

        this.description = description;

        this.url = url;

        this.httpMethod = httpMethod;

        this.type = type;

        this.orderNum = orderNum;
    }

    public void updateDetails(
            String description,
            String url,
            HttpMethod httpMethod,
            AuthorityType type,
            int orderNum
    ) {
        setDescription(description);

        setUrl(url);

        setHttpMethod(httpMethod);

        setType(type);

        setOrderNum(orderNum);
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void softDelete() {
        this.isDeleted = true;
        disable();
    }

    public void restore() {
        this.isDeleted = false;
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

        Menu menu = (Menu) o;

        return id != null && Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : 0;
    }

}
