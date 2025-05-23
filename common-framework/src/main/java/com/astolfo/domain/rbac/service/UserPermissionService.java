package com.astolfo.domain.rbac.service;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface UserPermissionService {

    List<PermissionId> findPermissionIdListByUser(User user);

    List<String> findPermissionSymbolNameByUser(User user);

    List<Permission> findPermissionListByUser(@Nonnull User user);

    List<Permission> findUserPermissionListByUsername(@Nonnull Username username);

    List<Permission> findUserPermissionListByEmail(@Nonnull Email email);

    List<Permission> findUserPermissionListById(@Nonnull UserId userId);

}
