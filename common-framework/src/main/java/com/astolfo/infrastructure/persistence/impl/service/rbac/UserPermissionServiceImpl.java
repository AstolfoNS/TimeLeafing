package com.astolfo.infrastructure.persistence.impl.service.rbac;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.PermissionId;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserPermissionService;
import com.astolfo.domain.rbac.service.UserRoleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private PermissionRepository permissionRepository;


    @Override
    public List<PermissionId> findPermissionIdListByUser(User user) {
        return userRoleService
                .findRoleListByUser(user)
                .stream()
                .flatMap(role -> role.getPermissionIdList().stream())
                .distinct()
                .toList();
    }

    @Override
    public List<String> findPermissionSymbolNameByUser(User user) {
        return findPermissionListByUser(user)
                .stream()
                .map(permission -> permission.getSymbol().getSymbol())
                .collect(Collectors.toList());
    }

    @Override
    public List<Permission> findPermissionListByUser(@Nonnull User user) {
        return permissionRepository.findPermissionListByIdList(findPermissionIdListByUser(user));
    }

    @Override
    public List<Permission> findUserPermissionListByUsername(@Nonnull Username username) {
        return findPermissionListByUser(Objects.requireNonNull(userRepository.findUserByUsername(username).orElse(null)));
    }

    @Override
    public List<Permission> findUserPermissionListByEmail(@Nonnull Email email) {
        return findPermissionListByUser(Objects.requireNonNull(userRepository.findUserByEmail(email).orElse(null)));
    }

    @Override
    public List<Permission> findUserPermissionListById(@Nonnull UserId userId) {
        return findPermissionListByUser(Objects.requireNonNull(userRepository.findUserById(userId).orElse(null)));
    }

}
