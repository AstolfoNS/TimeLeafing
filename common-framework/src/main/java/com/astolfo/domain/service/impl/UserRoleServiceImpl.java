package com.astolfo.domain.service.impl;

import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.domain.service.UserRoleService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
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
    public List<Role> findRoleListByUser(User user) {
        if (Objects.nonNull(user)) {
           return roleRepository.findRoleListByIdList(user.getRoleIdList());
        } else {
            return List.of();
        }
    }

    @Override
    public List<Role> findUserRoleListByUsername(@Nonnull String username) {
        return findRoleListByUser(userRepository.findUserByUsername(username).orElse(null));
    }

    @Override
    public List<Role> findUserRoleListById(@Nonnull Long id) {
        return findRoleListByUser(userRepository.findUserById(id).orElse(null));
    }
}
