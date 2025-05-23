package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleConverter {

    Role toDomain(RoleEntity roleEntity);

    List<Role> toDomain(List<RoleEntity> roleEntityList);

    RoleEntity toEntity(Role role);

    List<RoleEntity> toEntity(List<Role> roleList);
}
