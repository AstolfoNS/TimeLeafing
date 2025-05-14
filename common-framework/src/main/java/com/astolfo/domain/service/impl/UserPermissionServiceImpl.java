package com.astolfo.domain.service.impl;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.domain.service.RolePermissionService;
import com.astolfo.domain.service.UserPermissionService;
import com.astolfo.domain.service.UserRoleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPermissionServiceImpl implements UserPermissionService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;


    @Override
    public Set<Permission> findPermissionSetByUser(User user) {
        if (Objects.nonNull(user)) {
            return userRoleService
                    .findRoleListByUser(user)
                    .stream()
                    .filter(Objects::nonNull)
                    .flatMap(role -> rolePermissionService.findPermissionListByRole(role).stream())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }

    @Override
    public Set<Permission> findUserPermissionSetByUsername(@Nonnull String username) {
        return findPermissionSetByUser(userRepository.findUserByUsername(username).orElse(null));
    }

    @Override
    public Set<Permission> findUserPermissionSetById(@Nonnull Long id) {
        return findPermissionSetByUser(userRepository.findUserById(id).orElse(null));
    }

}
