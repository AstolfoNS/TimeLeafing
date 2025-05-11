package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> findMenuListByUserId(Long userId);

    List<Menu> findMenuListByUserUsername(String username);

    List<Menu> findMenuListByRoleId(Long roleId);

    List<Menu> findMenuListByRoleName(String roleName);

}
