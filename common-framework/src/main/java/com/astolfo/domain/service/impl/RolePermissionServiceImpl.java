package com.astolfo.domain.service.impl;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.service.RolePermissionService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    RoleRepository roleRepository;

    @Resource
    PermissionRepository permissionRepository;


    @Override
    public List<Permission> findPermissionListByRole(Role role) {
        if (Objects.nonNull(role)) {
            return permissionRepository.findPermissionListByIdList(role.getPermissionIdList());
        } else {
            return List.of();
        }
    }

    @Override
    public List<Permission> findRolePermissionListByName(@Nonnull String name) {
        return findPermissionListByRole(roleRepository.findRoleByName(name).orElse(null));
    }

    @Override
    public List<Permission> findRolePermissionListById(@Nonnull Long id) {
        return findPermissionListByRole(roleRepository.findRoleById(id).orElse(null));
    }
}
