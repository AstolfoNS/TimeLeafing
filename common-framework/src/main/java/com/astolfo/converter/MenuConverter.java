package com.astolfo.converter;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.model.valueobject.entity.Permission;
import com.astolfo.domain.rbac.model.valueobject.enumtype.AuthorityType;
import com.astolfo.domain.rbac.model.valueobject.enumtype.HttpMethod;
import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MenuConverter {

    public Optional<Menu> toDomain(MenuEntity menuEntity) {
        return Optional.of(new Menu(
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
                )
        );
    }

    public Optional<MenuEntity> toEntity(Menu menu) {
        return Optional.of(new MenuEntity(
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
                )
        );
    }

}
