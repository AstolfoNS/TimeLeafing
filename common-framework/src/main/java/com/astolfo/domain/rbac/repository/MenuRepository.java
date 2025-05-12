package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> findUserMenuListById(Long userId);

    List<Menu> findUserMenuListByUsername(String username);

    List<Menu> findRoleMenuListById(Long roleId);

    List<Menu> findRoleMenuListByName(String roleName);

}
