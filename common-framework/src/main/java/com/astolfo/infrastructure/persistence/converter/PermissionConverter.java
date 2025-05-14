package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Symbol;
import com.astolfo.domain.domain.rbac.model.valueobject.enumtype.HttpMethod;
import com.astolfo.domain.domain.rbac.model.valueobject.enumtype.Point;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionConverter {

    public Permission toDomain(PermissionEntity permissionEntity) {
        if (permissionEntity == null) {
            return null;
        } else {
            Permission permission = new Permission();

            permission.setId(permissionEntity.getId());

            permission.setSymbol(Symbol.of(permissionEntity.getSymbol()));

            permission.setDescription(permissionEntity.getDescription());

            permission.setUrl(permissionEntity.getUrl());

            permission.setHttpMethod(HttpMethod.of(permissionEntity.getHttpMethod()));

            permission.setPoint(Point.of(permissionEntity.getPoint()));

            permission.setOrderNum(permissionEntity.getOrderNum());

            permission.setEnabled(permissionEntity.getEnabled());

            permission.setCreateTime(permissionEntity.getCreateTime());

            permission.setUpdateTime(permissionEntity.getUpdateTime());

            permission.setIsDeleted(permissionEntity.getIsDeleted());

            return permission;
        }
    }

    public PermissionEntity toEntity(Permission permission) {
        if (permission == null) {
            return null;
        } else {
            PermissionEntity permissionEntity = new PermissionEntity();

            permissionEntity.setId(permission.getId());

            permissionEntity.setSymbol(permission.getSymbol().getSymbol());

            permissionEntity.setDescription(permission.getDescription());

            permissionEntity.setUrl(permission.getUrl());

            permissionEntity.setUrl(permission.getUrl());

            permissionEntity.setHttpMethod(permission.getHttpMethod().getHttpMethod());

            permissionEntity.setPoint(permission.getPoint().getPoint());

            permissionEntity.setOrderNum(permission.getOrderNum());

            permissionEntity.setEnabled(permission.getEnabled());

            permissionEntity.setCreateTime(permission.getCreateTime());

            permissionEntity.setUpdateTime(permission.getUpdateTime());

            permissionEntity.setIsDeleted(permission.getIsDeleted());

            return permissionEntity;
        }
    }

    public List<Permission> toDomain(List<PermissionEntity> permissionEntityList) {
        if (permissionEntityList == null) {
            return null;
        } else {
            return permissionEntityList
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        }
    }

    public List<PermissionEntity> toEntity(List<Permission> permissionList) {
        if (permissionList == null) {
            return null;
        } else {
            return permissionList
                    .stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
    }

}
