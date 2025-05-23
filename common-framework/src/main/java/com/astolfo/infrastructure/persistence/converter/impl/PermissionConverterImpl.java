package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.valueobject.*;
import com.astolfo.infrastructure.persistence.converter.PermissionConverter;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PermissionConverterImpl implements PermissionConverter {

    @Override
    public Permission toDomain(PermissionEntity permissionEntity) {
        Permission.Details details = new Permission.Details();

        details.setId(PermissionId.of(permissionEntity.getId()));

        details.setSymbol(Symbol.of(permissionEntity.getSymbol()));

        details.setDescription(permissionEntity.getDescription());

        details.setUrl(UrlPattern.of(permissionEntity.getUrl()));

        details.setHttpMethod(HttpMethod.of(permissionEntity.getHttpMethod()));

        details.setPoint(Point.of(permissionEntity.getPoint()));

        details.setOrderNum(permissionEntity.getOrderNum());

        details.setEnabled(permissionEntity.getEnabled());

        details.setCreateTime(permissionEntity.getCreateTime());

        details.setUpdateTime(permissionEntity.getUpdateTime());

        details.setIsDeleted(permissionEntity.getIsDeleted());

        return Permission.of(details);
    }

    @Override
    public List<Permission> toDomain(List<PermissionEntity> permissionEntityList) {
        if (Objects.isNull(permissionEntityList)) {
            return List.of();
        } else {
            return permissionEntityList
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        }
    }

    @Override
    public PermissionEntity toEntity(Permission permission) {
        PermissionEntity permissionEntity = new PermissionEntity();

        permissionEntity.setId(permission.getId().getPermissionId());

        permissionEntity.setSymbol(permission.getSymbol().getSymbol());

        permissionEntity.setDescription(permission.getDescription());

        permissionEntity.setUrl(permission.getUrl().getUrlPattern());

        permissionEntity.setHttpMethod(permission.getHttpMethod().getHttpMethod());

        permissionEntity.setPoint(permission.getPoint().getPoint());

        permissionEntity.setOrderNum(permission.getOrderNum());

        permissionEntity.setEnabled(permission.getIsDeleted());

        permissionEntity.setCreateTime(permission.getCreateTime());

        permissionEntity.setUpdateTime(permission.getUpdateTime());

        permissionEntity.setIsDeleted(permission.getIsDeleted());

        return permissionEntity;
    }

    @Override
    public List<PermissionEntity> toEntity(List<Permission> permissionList) {
        if (Objects.isNull(permissionList)) {
            return List.of();
        }  else {
            return permissionList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        }
    }
}
