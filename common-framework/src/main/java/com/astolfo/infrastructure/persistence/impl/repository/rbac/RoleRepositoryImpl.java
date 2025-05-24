package com.astolfo.infrastructure.persistence.impl.repository.rbac;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.persistence.converter.RoleConverter;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleConverter roleConverter;


    private RoleEntity findRoleEntityById(RoleId roleId) {
        return roleMapper.selectRoleEntityById(roleId.getRoleId());
    }

    private RoleEntity findRoleEntityByRoleName(RoleName roleName) {
        return roleMapper.selectRoleEntityByName(roleName.getRoleName());
    }

    private List<RoleEntity> findRoleEntityListByIdList(List<RoleId> roleIdList) {
        return roleMapper.selectRoleEntityListByIdList(RoleId.toLong(roleIdList));
    }

    private RoleEntity findRoleEntityWithoutPermissionIdListById(RoleId roleId) {
        return roleMapper.selectById(roleId.getRoleId());
    }

    private RoleEntity findRoleEntityWithoutPermissionIdListByRoleName(RoleName roleName) {
         return roleMapper.selectOne(Wrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getRoleName, roleName.getRoleName()));
    }

    private List<RoleEntity> findRoleEntityListWithoutPermissionIdListByIdList(List<RoleId> roleIdList) {
        return roleMapper.selectByIds(RoleId.toLong(roleIdList));
    }

    @Override
    public Optional<Role> findRoleById(@Nonnull RoleId roleId) {
        return Optional.ofNullable(roleConverter.toDomain(findRoleEntityById(roleId)));
    }

    @Override
    public Optional<Role> findRoleByRoleName(@Nonnull RoleName roleName) {
        return Optional.ofNullable(roleConverter.toDomain(findRoleEntityByRoleName(roleName)));
    }

    @Override
    public List<Role> findRoleListByIdList(@Nonnull List<RoleId> roleIdList) {
        if (roleIdList.isEmpty()) {
            return List.of();
        } else {
            return roleConverter.toDomain(findRoleEntityListByIdList(roleIdList));
        }
    }

    @Override
    public Optional<Role> findRoleWithoutPermissionIdListById(@NotNull RoleId roleId) {
        return Optional.ofNullable(roleConverter.toDomain(findRoleEntityWithoutPermissionIdListById(roleId)));
    }

    @Override
    public Optional<Role> findRoleWithoutPermissionIdListByRoleName(@NotNull RoleName roleName) {
        return Optional.ofNullable(roleConverter.toDomain(findRoleEntityWithoutPermissionIdListByRoleName(roleName)));
    }

    @Override
    public List<Role> findRoleListWithoutPermissionIdListByIdList(@NotNull List<RoleId> roleIdList) {
        if (roleIdList.isEmpty()) {
            return List.of();
        } else {
            return roleConverter.toDomain(findRoleEntityListWithoutPermissionIdListByIdList(roleIdList));
        }
    }

    @Transactional
    @Override
    public Role save(@Nonnull Role role) {
        try {
            RoleEntity roleEntity = roleConverter.toEntity(role);

            roleMapper.insertOrUpdate(roleEntity);

            return roleConverter.toDomain(roleEntity);
        } catch (Exception exception) {
            throw new RuntimeException("保存Role时发生错误", exception);
        }
    }
}
