package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import com.astolfo.infrastructure.persistence.mapper.MenuMapper;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Resource
    RoleMapper roleMapper;

    @Resource
    MenuMapper menuMapper;


    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Menu> getMenuByUsername(String username) {
        return List.of();
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public List<Menu> getMenuByRoleName(String roleName) {
        return List.of();
    }

    @Override
    public Menu toMenu(MenuEntity menuEntity) {
        return null;
    }
}
