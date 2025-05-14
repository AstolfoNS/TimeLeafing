package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.astolfo.infrastructure.persistence.mapper.RolePermissionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    @Resource
    RolePermissionMapper rolePermissionMapper;


    public Role toDomain(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        } else {
            Role role = new Role();

            role.setId(roleEntity.getId());

            role.setName(roleEntity.getName());

            role.setDescription(roleEntity.getDescription());

            role.setPermissionIdList(roleEntity.getPermissionIdList());

            role.setEnabled(roleEntity.getEnabled());

            role.setCreateTime(roleEntity.getCreateTime());

            role.setUpdateTime(roleEntity.getUpdateTime());

            role.setIsDeleted(roleEntity.getIsDeleted());

            return role;
        }
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        } else {
            RoleEntity roleEntity = new RoleEntity();

            roleEntity.setId(role.getId());

            roleEntity.setName(role.getName());

            roleEntity.setDescription(role.getDescription());

            roleEntity.setPermissionIdList(role.getPermissionIdList());

            roleEntity.setEnabled(role.getEnabled());

            roleEntity.setCreateTime(role.getCreateTime());

            roleEntity.setUpdateTime(role.getUpdateTime());

            roleEntity.setIsDeleted(role.getIsDeleted());

            return roleEntity;
        }
    }

    public List<Role> toDomain(List<RoleEntity> roleEntityList) {
        if (roleEntityList == null) {
            return null;
        } else {
            return roleEntityList
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        }
    }

    public List<RoleEntity> toEntity(List<Role> roleList) {
        if (roleList == null) {
            return null;
        } else {
            return roleList
                    .stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
    }

}
