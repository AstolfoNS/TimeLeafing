package com.astolfo.domain.rbac.model.root;

import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Setter(AccessLevel.PRIVATE)
@Getter
public class Role {

    // 唯一标识
    private RoleId id;

    // 基础属性
    private RoleName roleName;

    private String description;

    private List<PermissionId> permissionIdList = List.of();

    // 生命周期属性
    private Boolean enabled;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;

    @Data
    public static class Details {

        // 唯一标识
        private RoleId id;

        // 基础属性
        private RoleName roleName;

        private String description;

        private List<PermissionId> permissionIdList;

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
            return id.getRoleId();
        }
    }

    public String getRoleNameString() {
        if (Objects.isNull(roleName)) {
            return null;
        } else {
            return roleName.getRoleName();
        }
    }

    private Role(Details details) {
        setId(details.getId());

        setRoleName(details.getRoleName());

        setDescription(details.getDescription());

        setPermissionIdList(details.getPermissionIdList());

        setEnabled(details.getEnabled());

        setCreateTime(details.getCreateTime());

        setUpdateTime(details.getUpdateTime());

        setIsDeleted(details.getIsDeleted());
    }

    public static Role of(Details details) {
        return new Role(details);
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

        Role role = (Role) o;

        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
