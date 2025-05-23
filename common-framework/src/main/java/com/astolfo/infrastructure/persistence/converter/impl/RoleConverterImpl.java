package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.infrastructure.persistence.converter.RoleConverter;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public Role toDomain(RoleEntity roleEntity) {
        // TODO: toDomain

        return null;
    }

    @Override
    public List<Role> toDomain(List<RoleEntity> roleEntityList) {
        // TODO: toDomain

        return List.of();
    }

    @Override
    public RoleEntity toEntity(Role role) {
        // TODO: toEntity

        return null;
    }

    @Override
    public List<RoleEntity> toEntity(List<Role> roleList) {
        // TODO: toEntity

        return List.of();
    }
}
