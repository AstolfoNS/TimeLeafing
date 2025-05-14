package com.astolfo.domain.service;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface UserPermissionService {

    List<Permission> findPermissionListByUser(User user);

    List<Permission> findUserPermissionListByUsername(@Nonnull String username);

    List<Permission> findUserPermissionListByEmail(@Nonnull Email email);

    List<Permission> findUserPermissionListById(@Nonnull Long id);

}
