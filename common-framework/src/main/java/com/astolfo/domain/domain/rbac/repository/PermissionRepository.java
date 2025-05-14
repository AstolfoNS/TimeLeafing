package com.astolfo.domain.domain.rbac.repository;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Symbol;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {

    Optional<Permission> findPermissionById(@Nonnull Long id);

    Optional<Permission> findPermissionBySymbol(@Nonnull Symbol symbol);

    List<Permission> findPermissionListByIdList(@Nonnull List<Long> idList);

    Permission save(@Nonnull Permission permission);

}
