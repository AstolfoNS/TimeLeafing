package com.astolfo.domain.service.impl;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.domain.service.UserPermissionService;
import com.astolfo.domain.service.UserRoleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private PermissionRepository permissionRepository;


    @Override
    public List<Permission> findPermissionListByUser(User user) {
        if (Objects.nonNull(user)) {
            List<Long> permissionIdList = userRoleService
                    .findRoleListByUser(user)
                    .stream()
                    .flatMap(role -> role.getPermissionIdList().stream())
                    .distinct()
                    .toList();

            return permissionRepository.findPermissionListByIdList(permissionIdList);
        } else {
            return List.of();
        }
    }

    @Override
    public List<Permission> findUserPermissionListByUsername(@Nonnull String username) {
        return findPermissionListByUser(userRepository.findUserByUsername(username).orElse(null));
    }

    @Override
    public List<Permission> findUserPermissionListByEmail(@Nonnull Email email) {
        return findPermissionListByUser(userRepository.findUserByEmail(email).orElse(null));
    }

    @Override
    public List<Permission> findUserPermissionListById(@Nonnull Long id) {
        return findPermissionListByUser(userRepository.findUserById(id).orElse(null));
    }

}
