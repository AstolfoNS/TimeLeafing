package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findRoleById(@Nonnull RoleId roleId);

    Optional<Role> findRoleByRoleName(@Nonnull RoleName roleName);

    List<Role> findRoleListByIdList(@Nonnull List<RoleId> roleIdList);

    Optional<Role> findRoleWithoutPermissionIdListById(@Nonnull RoleId roleId);

    Optional<Role> findRoleWithoutPermissionIdListByRoleName(@Nonnull RoleName roleName);

    List<Role> findRoleListWithoutPermissionIdListByIdList(@Nonnull List<RoleId> roleIdList);

    Role save(@Nonnull Role role);

}
