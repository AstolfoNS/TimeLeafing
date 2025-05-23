package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;

import java.util.List;

public interface PermissionConverter {

    Permission toDomain(PermissionEntity permissionEntity);

    List<Permission> toDomain(List<PermissionEntity> permissionEntityList);

    PermissionEntity toEntity(Permission permission);

    List<PermissionEntity> toEntity(List<Permission> permissionList);

}
