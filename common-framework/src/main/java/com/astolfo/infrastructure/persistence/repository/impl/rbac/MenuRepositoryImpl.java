package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.infrastructure.converter.MenuConverter;
import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.repository.MenuRepository;
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

    @Resource
    MenuConverter menuConverter;


    @Override
    public List<Menu> findUserMenuListById(Long id) {
        return menuConverter.toDomain(menuMapper.findUserMenuEntityListById(id));
    }

    @Override
    public List<Menu> findUserMenuListByUsername(String username) {
        return menuConverter.toDomain(menuMapper.findUserMenuEntityListByUsername(username));
    }

    @Override
    public List<Menu> findRoleMenuListById(Long id) {
        return List.of();
    }

    @Override
    public List<Menu> findRoleMenuListByName(String name) {
        return List.of();
    }
}
