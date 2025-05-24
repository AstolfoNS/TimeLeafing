package com.astolfo.domain.rbac.service;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface RolePermissionService {

    List<Permission> findPermissionListByRole(@Nonnull Role role);

    List<Permission> findRolePermissionListByName(@Nonnull RoleName roleName);

    List<Permission> findRolePermissionListById(@Nonnull RoleId roleId);

    void addPermissionToRole(@Nonnull PermissionId permissionId, @Nonnull RoleId roleId);

    void removePermissionFromRole(@Nonnull PermissionId permissionId, @Nonnull RoleId roleId);

}
