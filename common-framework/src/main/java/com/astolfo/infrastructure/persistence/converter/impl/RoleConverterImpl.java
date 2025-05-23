package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import com.astolfo.infrastructure.persistence.converter.RoleConverter;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public Role toDomain(RoleEntity roleEntity) {
        Role.Details details = new Role.Details();

        // 转化
        details.setId(RoleId.of(roleEntity.getId()));

        details.setRoleName(RoleName.of(roleEntity.getRoleName()));

        details.setDescription(roleEntity.getDescription());

        details.setPermissionIdList(PermissionId.toId(roleEntity.getPermissionIdList()));

        details.setEnabled(roleEntity.getEnabled());

        details.setCreateTime(roleEntity.getCreateTime());

        details.setUpdateTime(roleEntity.getUpdateTime());

        details.setIsDeleted(roleEntity.getIsDeleted());

        // 创建
        return Role.of(details);
    }

    @Override
    public List<Role> toDomain(List<RoleEntity> roleEntityList) {
        return roleEntityList
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public RoleEntity toEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();

        // 转化
        roleEntity.setId(role.getId().getRoleId());

        roleEntity.setRoleName(role.getRoleName().getRoleName());

        roleEntity.setDescription(role.getDescription());

        roleEntity.setPermissionIdList(PermissionId.toLong(role.getPermissionIdList()));

        roleEntity.setEnabled(role.getIsDeleted());

        roleEntity.setCreateTime(role.getCreateTime());

        roleEntity.setUpdateTime(role.getUpdateTime());

        roleEntity.setIsDeleted(role.getIsDeleted());

        // 输出
        return roleEntity;
    }

    @Override
    public List<RoleEntity> toEntity(List<Role> roleList) {
        return roleList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
