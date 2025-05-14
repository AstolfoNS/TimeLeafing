package com.astolfo.domain.domain.rbac.repository;

import com.astolfo.domain.domain.rbac.model.Role;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findRoleById(@Nonnull Long id);

    Optional<Role> findRoleByName(@Nonnull String name);

    List<Role> findRoleListByIdList(@Nonnull List<Long> idList);

    Role save(@Nonnull Role role);

}
