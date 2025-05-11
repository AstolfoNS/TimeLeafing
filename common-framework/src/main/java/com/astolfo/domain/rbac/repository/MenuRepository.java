package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.infrastructure.persistence.entity.MenuEntity;

import java.util.List;

public interface MenuRepository {

    List<Menu> getMenuByUserId(Long userId);

    List<Menu> getMenuByUsername(String username);

    List<Menu> getMenuByRoleId(Long roleId);

    List<Menu> getMenuByRoleName(String roleName);

    Menu toMenu(MenuEntity menuEntity);

}
