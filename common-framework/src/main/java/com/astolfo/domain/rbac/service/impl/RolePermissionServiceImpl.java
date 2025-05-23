package com.astolfo.domain.rbac.service.impl;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.RoleName;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.rbac.service.RolePermissionService;
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
    public List<Permission> findPermissionListByRole(@Nonnull Role role) {
        return permissionRepository.findPermissionListByIdList(role.getPermissionIdList());
    }

    @Override
    public List<Permission> findRolePermissionListByName(@Nonnull RoleName roleName) {
        return findPermissionListByRole(Objects.requireNonNull(roleRepository.findRoleByRoleName(roleName).orElse(null)));
    }

    @Override
    public List<Permission> findRolePermissionListById(@Nonnull RoleId roleId) {
        return findPermissionListByRole(Objects.requireNonNull(roleRepository.findRoleById(roleId).orElse(null)));
    }
}
