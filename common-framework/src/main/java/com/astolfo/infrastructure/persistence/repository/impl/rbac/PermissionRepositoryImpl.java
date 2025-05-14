package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.domain.rbac.model.valueobject.entity.Symbol;
import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
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

    private PermissionEntity findPermissionEntityById(Long id) {
        return permissionMapper.selectById(id);
    }

    private List<PermissionEntity> findPermissionEntityListByIdList(List<Long> idList) {
        return permissionMapper.selectByIds(idList);
    }

    @Override
    public Optional<Permission> findPermissionBySymbol(@Nonnull Symbol symbol) {
        return Optional.ofNullable(permissionConverter.toDomain(findPermissionEntityBySymbol(symbol)));
    }

    @Override
    public Optional<Permission> findPermissionById(@Nonnull Long id) {
        return Optional.ofNullable(permissionConverter.toDomain(findPermissionEntityById(id)));
    }

    @Override
    public List<Permission> findPermissionListByIdList(@Nonnull List<Long> idList) {
        return permissionConverter.toDomain(findPermissionEntityListByIdList(idList));
    }

    @Override
    public Permission save(@Nonnull Permission permission) {
        return null;
    }
}

