package com.astolfo.infrastructure.persistence.impl.service.rbac;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.rbac.service.RolePermissionService;
import com.astolfo.infrastructure.persistence.entity.RolePermissionEntity;
import com.astolfo.infrastructure.persistence.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    RoleRepository roleRepository;

    @Resource
    PermissionRepository permissionRepository;

    @Resource
    RolePermissionMapper rolePermissionMapper;


    @Override
    public List<Permission> findPermissionListByRole(@Nonnull Role role) {
        return permissionRepository.findPermissionListByIdList(role.getPermissionIdList());
    }

    @Override
    public List<Permission> findRolePermissionListByName(@Nonnull RoleName roleName) {
        return findPermissionListByRole(Objects.requireNonNull(roleRepository.findRoleByRoleName(roleName).orElse(null)));
    }

    @Override
    public List<Permission> findRolePermissionListById(@Nonnull RoleId roleId) {
        return findPermissionListByRole(Objects.requireNonNull(roleRepository.findRoleById(roleId).orElse(null)));
    }

    @Transactional
    @Override
    public void addPermissionToRole(@NotNull PermissionId permissionId, @NotNull RoleId roleId) {
        try {
            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();

            rolePermissionEntity.setPermissionId(permissionId.getPermissionId());

            rolePermissionEntity.setRoleId(roleId.getRoleId());

            rolePermissionMapper.insert(rolePermissionEntity);
        } catch (Exception exception) {
            throw new RuntimeException("为角色添加权限时发生错误", exception);
        }
    }

    @Transactional
    @Override
    public void removePermissionFromRole(@NotNull PermissionId permissionId, @NotNull RoleId roleId) {
        try {
            LambdaQueryWrapper<RolePermissionEntity> queryWrapper = new LambdaQueryWrapper<>();

            queryWrapper.eq(RolePermissionEntity::getPermissionId, permissionId.getPermissionId());

            queryWrapper.eq(RolePermissionEntity::getRoleId, roleId.getRoleId());

            rolePermissionMapper.delete(queryWrapper);
        } catch (Exception exception) {
            throw new RuntimeException("为角色删除权限时发生错误", exception);
        }
    }
}
