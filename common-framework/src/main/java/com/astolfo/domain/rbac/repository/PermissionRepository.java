package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Permission;
import com.astolfo.domain.rbac.model.valueobject.entity.Symbol;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {

    Optional<Permission> findPermissionById(Long id);

    Optional<Permission> findPermissionBySymbol(Symbol symbol);

    List<Optional<Permission>> findPermissionListByIdList(List<Long> idList);

    Permission save(Permission permission);

}
