package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.infrastructure.persistence.converter.PermissionConverter;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionConverterImpl implements PermissionConverter {


    @Override
    public Permission toDomain(PermissionEntity permissionEntity) {
        // TODO: toDomain

        return null;
    }

    @Override
    public List<Permission> toDomain(List<PermissionEntity> permissionEntityList) {
        // TODO: toDomain

        return List.of();
    }

    @Override
    public PermissionEntity toEntity(Permission permission) {
        // TODO: toEntity

        return null;
    }

    @Override
    public List<PermissionEntity> toEntity(List<Permission> permissionList) {
        // TODO: toEntity

        return List.of();
    }
}
