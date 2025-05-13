package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.infrastructure.converter.MenuConverter;
import com.astolfo.domain.rbac.model.Permission;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.infrastructure.persistence.mapper.MenuMapper;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepositoryImpl implements PermissionRepository {

    @Resource
    RoleMapper roleMapper;

    @Resource
    MenuMapper menuMapper;

    @Resource
    MenuConverter menuConverter;


    @Override
    public List<Permission> findUserMenuListById(Long id) {
        return menuConverter.toDomain(menuMapper.findUserMenuEntityListById(id));
    }

    @Override
    public List<Permission> findUserMenuListByUsername(String username) {
        return menuConverter.toDomain(menuMapper.findUserMenuEntityListByUsername(username));
    }

    @Override
    public List<Permission> findRoleMenuListById(Long id) {
        return List.of();
    }

    @Override
    public List<Permission> findRoleMenuListByName(String name) {
        return List.of();
    }
}
