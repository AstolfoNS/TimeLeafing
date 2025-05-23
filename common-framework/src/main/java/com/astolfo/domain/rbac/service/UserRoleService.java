package com.astolfo.domain.rbac.service;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface UserRoleService {

    List<Role> findRoleListByUser(@Nonnull User user);

    List<Role> findUserRoleListByUsername(@Nonnull Username username);

    List<Role> findUserRoleListById(@Nonnull UserId userId);

}
