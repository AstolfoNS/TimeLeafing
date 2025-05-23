package com.astolfo.domain.rbac.service.impl;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserRoleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;


    @Override
    public List<Role> findRoleListByUser(@NotNull User user) {
        return roleRepository.findRoleListByIdList(user.getRoleIdList());
    }

    @Override
    public List<Role> findUserRoleListByUsername(@Nonnull Username username) {
        return findRoleListByUser(Objects.requireNonNull(userRepository.findUserByUsername(username).orElse(null)));
    }

    @Override
    public List<Role> findUserRoleListById(@Nonnull UserId userId) {
        return findRoleListByUser(Objects.requireNonNull(userRepository.findUserById(userId).orElse(null)));
    }
}
