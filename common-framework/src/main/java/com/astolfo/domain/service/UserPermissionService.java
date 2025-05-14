package com.astolfo.domain.service;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import jakarta.annotation.Nonnull;

import java.util.Set;

public interface UserPermissionService {

    Set<Permission> findPermissionSetByUser(User user);

    Set<Permission> findUserPermissionSetById(@Nonnull Long id);

    Set<Permission> findUserPermissionSetByUsername(@Nonnull String username);

}
