package com.astolfo.converter;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.model.valueobject.entity.Permission;
import com.astolfo.domain.rbac.model.valueobject.enumtype.AuthorityType;
import com.astolfo.domain.rbac.model.valueobject.enumtype.HttpMethod;
import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class MenuConverter {

    public Menu toDomain(MenuEntity menuEntity) {
        if (menuEntity == null) {
            return null;
        } else {
            return new Menu(
                    menuEntity.getId(),
                    Permission.of(menuEntity.getPermission()),
                    menuEntity.getDescription(),
                    menuEntity.getUrl(),
                    HttpMethod.get(menuEntity.getPermission()),
                    AuthorityType.get(menuEntity.getAuthorityType()),
                    menuEntity.getOrderNum(),
                    menuEntity.getEnabled(),
                    menuEntity.getCreateTime(),
                    menuEntity.getUpdateTime(),
                    menuEntity.getIsDeleted()
            );
        }
    }

    public MenuEntity toEntity(Menu menu) {
        if (menu == null) {
            return null;
        } else {
            return new MenuEntity(
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

    public List<Menu> toDomain(List<MenuEntity> menuEntityList) {
        return menuEntityList
                .stream()
                .map(menuEntity -> Optional.of(toDomain(menuEntity)).orElseThrow(() -> new NoSuchElementException("Can not convert null menuEntity")))
                .toList();
    }

    public List<MenuEntity> toEntity(List<Menu> menuList) {
        return menuList
                .stream()
                .map(menu -> Optional.of(toEntity(menu)).orElseThrow(() -> new NoSuchElementException("Can not convert null menu")))
                .toList();
    }

}
