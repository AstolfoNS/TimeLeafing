package com.astolfo.infrastructure.converter;

import com.astolfo.domain.rbac.model.Permission;
import com.astolfo.domain.rbac.model.valueobject.enumtype.PermissionPoint;
import com.astolfo.domain.rbac.model.valueobject.enumtype.HttpMethod;
import com.astolfo.infrastructure.persistence.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 实现 domain层的model <-> infra层的entity
 *
 *
 */
@Component
public class MenuConverter {

    public Permission toDomain(PermissionEntity menuEntity) {
        if (menuEntity == null) {
            return null;
        } else {
            return new Permission(
                    menuEntity.getId(),
                    Permission.of(menuEntity.getPermission()),
                    menuEntity.getDescription(),
                    menuEntity.getUrl(),
                    HttpMethod.get(menuEntity.getPermission()),
                    PermissionPoint.get(menuEntity.getAuthorityType()),
                    menuEntity.getOrderNum(),
                    menuEntity.getEnabled(),
                    menuEntity.getCreateTime(),
                    menuEntity.getUpdateTime(),
                    menuEntity.getIsDeleted()
            );
        }
    }

    public PermissionEntity toEntity(Permission menu) {
        if (menu == null) {
            return null;
        } else {
            return new PermissionEntity(
                    menu.getId(),
                    menu.getPermission().getPermissionName(),
                    menu.getDescription(),
                    menu.getUrl(),
                    menu.getHttpMethod().getHttpMethodName(),
                    menu.getAuthorityType().getAuthorityTypeName(),
                    menu.getOrderNum(),
                    menu.getEnabled(),
                    menu.getCreateTime(),
                    menu.getUpdateTime(),
                    menu.getIsDeleted()
            );
        }
    }

    public List<Permission> toDomain(List<PermissionEntity> menuEntityList) {
        return menuEntityList
                .stream()
                .map(menuEntity -> Optional.of(toDomain(menuEntity)).orElseThrow(() -> new NoSuchElementException("Can not convert null menuEntity")))
                .collect(Collectors.toList());
    }

    public List<PermissionEntity> toEntity(List<Permission> menuList) {
        return menuList
                .stream()
                .map(menu -> Optional.of(toEntity(menu)).orElseThrow(() -> new NoSuchElementException("Can not convert null menu")))
                .collect(Collectors.toList());
    }

}
