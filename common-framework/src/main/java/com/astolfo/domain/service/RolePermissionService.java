package com.astolfo.domain.service;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.Role;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface RolePermissionService {

    List<Permission> findPermissionListByRole(Role role);

    List<Permission> findRolePermissionListByName(@Nonnull String name);

    List<Permission> findRolePermissionListById(@Nonnull Long id);

}
