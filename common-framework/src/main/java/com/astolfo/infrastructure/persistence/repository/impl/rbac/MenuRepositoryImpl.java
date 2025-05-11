package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.converter.MenuConverter;
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
    public List<Menu> findMenuListByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Menu> findMenuListByUsername(String username) {
        return menuConverter.toDomain(menuMapper.findMenuEntityListByUserUsername(username));
    }

    @Override
    public List<Menu> findMenuListByRoleId(Long roleId) {
        return List.of();
    }

    @Override
    public List<Menu> findMenuListByRoleName(String roleName) {
        return List.of();
    }
}
