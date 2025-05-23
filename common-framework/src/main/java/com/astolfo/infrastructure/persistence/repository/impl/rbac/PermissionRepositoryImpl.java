package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.Symbol;
import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.infrastructure.persistence.converter.PermissionConverter;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;
import com.astolfo.infrastructure.persistence.mapper.PermissionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private PermissionConverter permissionConverter;


    private PermissionEntity findPermissionEntityBySymbol(Symbol symbol) {
        return permissionMapper.selectOne(Wrappers.<PermissionEntity>lambdaQuery().eq(PermissionEntity::getSymbol, symbol.getSymbol()));
    }

    private PermissionEntity findPermissionEntityById(PermissionId permissionId) {
        return permissionMapper.selectById(permissionId.getPermissionId());
    }

    private List<PermissionEntity> findPermissionEntityListByIdList(List<PermissionId> permissionIdList) {
        return permissionMapper.selectByIds(PermissionId.toLong(permissionIdList));
    }

    @Override
    public Optional<Permission> findPermissionBySymbol(@Nonnull Symbol symbol) {
        return Optional.ofNullable(permissionConverter.toDomain(findPermissionEntityBySymbol(symbol)));
    }

    @Override
    public Optional<Permission> findPermissionById(@Nonnull PermissionId permissionId) {
        return Optional.ofNullable(permissionConverter.toDomain(findPermissionEntityById(permissionId)));
    }

    @Override
    public List<Permission> findPermissionListByIdList(@Nonnull List<PermissionId> permissionIdList) {
        return permissionConverter.toDomain(findPermissionEntityListByIdList(permissionIdList));
    }

    @Override
    public Permission save(@Nonnull Permission permission) {
        return null;
    }
}

