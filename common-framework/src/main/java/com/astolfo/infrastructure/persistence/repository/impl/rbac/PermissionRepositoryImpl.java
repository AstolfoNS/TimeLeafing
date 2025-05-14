package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.domain.rbac.model.valueobject.entity.Symbol;
import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    @Override
    public Optional<Permission> findPermissionById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Permission> findPermissionBySymbol(Symbol symbol) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Permission>> findPermissionListByIdList(List<Long> idList) {
        return List.of();
    }

    @Override
    public Permission save(Permission permission) {
        return null;
    }
}

