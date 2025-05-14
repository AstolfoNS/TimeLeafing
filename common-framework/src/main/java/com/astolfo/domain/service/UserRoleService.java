package com.astolfo.domain.service;

import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.domain.domain.rbac.model.User;
import jakarta.annotation.Nonnull;

import java.util.List;

public interface UserRoleService {

    List<Role> findRoleListByUser(User user);

    List<Role> findUserRoleListByUsername(@Nonnull String username);

    List<Role> findUserRoleListById(@Nonnull Long id);

}
