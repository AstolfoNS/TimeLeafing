package com.astolfo.infrastructure.persistence.impl.service.rbac;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.RoleId;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserRoleService;
import com.astolfo.infrastructure.persistence.entity.UserRoleEntity;
import com.astolfo.infrastructure.persistence.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleMapper userRoleMapper;


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

    @Transactional
    @Override
    public void addRoleToUser(@NotNull RoleId roleId, @NotNull UserId userId) {
        try {
            UserRoleEntity userRoleEntity = new UserRoleEntity();

            userRoleEntity.setRoleId(roleId.getRoleId());

            userRoleEntity.setUserId(userId.getUserId());

            userRoleMapper.insert(userRoleEntity);
        } catch (Exception exception) {
            throw new RuntimeException("为用户添加角色是发生错误", exception);
        }
    }

    @Transactional
    @Override
    public void removeRoleFromUser(@NotNull RoleId roleId, @NotNull UserId userId) {
        try {
            LambdaQueryWrapper<UserRoleEntity> queryWrapper = Wrappers.<UserRoleEntity>lambdaQuery();

            queryWrapper.eq(UserRoleEntity::getRoleId, roleId.getRoleId());

            queryWrapper.eq(UserRoleEntity::getUserId, userId.getUserId());

            userRoleMapper.delete(queryWrapper);
        } catch (Exception exception) {
            throw new RuntimeException("为用户删除角色时发生错误", exception);
        }
    }
}
