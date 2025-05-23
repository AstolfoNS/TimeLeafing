package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.Symbol;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {

    Optional<Permission> findPermissionById(@Nonnull PermissionId permissionId);

    Optional<Permission> findPermissionBySymbol(@Nonnull Symbol symbol);

    List<Permission> findPermissionListByIdList(@Nonnull List<PermissionId> permissionIdList);

    Permission save(@Nonnull Permission permission);

}
