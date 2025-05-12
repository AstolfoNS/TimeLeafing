package com.astolfo.converter;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleConverter {

    public Role toDomain(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        } else {
            return new Role(
                    roleEntity.getId(),
                    roleEntity.getName(),
                    roleEntity.getDescription(),
                    roleEntity.getEnabled(),
                    roleEntity.getCreateTime(),
                    roleEntity.getUpdateTime(),
                    roleEntity.getIsDeleted()
            );
        }
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        } else {
            return new RoleEntity(
                    role.getId(),
                    role.getName(),
                    role.getDescription(),
                    role.getEnabled(),
                    role.getCreateTime(),
                    role.getUpdateTime(),
                    role.getIsDeleted()
            );
        }
    }

    public List<Role> toDomain(List<RoleEntity> roleEntityList) {
        return roleEntityList
                .stream()
                .map(roleEntity -> Optional.of(toDomain(roleEntity)).orElseThrow(() -> new NoSuchElementException("Can not convert null roleEntity")))
                .collect(Collectors.toList());
    }

    public List<RoleEntity> toEntity(List<Role> roleList) {
        return roleList
                .stream()
                .map(role -> Optional.of(toEntity(role)).orElseThrow(() -> new NoSuchElementException("Can not convert null role")))
                .collect(Collectors.toList());
    }

}
