package com.astolfo.infrastructure.persistence.impl.repository.rbac;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.persistence.converter.UserConverter;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.astolfo.infrastructure.persistence.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;


    private UserEntity findUserEntityByUsername(Username username) {
        return userMapper.selectUserEntityByUsername(username.getUsername());
    }

    private UserEntity findUserEntityByEmail(Email email) {
        return userMapper.selectUserEntityByEmail(email.getEmail());
    }

    private UserEntity findUserEntityById(UserId userId) {
        return userMapper.selectUserEntityById(userId.getUserId());
    }

    private List<UserEntity> findUserEntityListByIdList(List<UserId> userIdList) {
        return userMapper.selectUserEntityListByIdList(UserId.toLong(userIdList));
    }

    private UserEntity findUserEntityWithoutRoleIdListByUsername(Username username) {
        return userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username.getUsername()));
    }

    private UserEntity findUserEntityWithoutRoleIdListByEmail(Email email) {
        return userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getEmail, email.getEmail()));
    }

    private UserEntity findUserEntityWithoutRoleIdListById(UserId userId) {
        return userMapper.selectById(userId.getUserId());
    }

    private List<UserEntity> findUserEntityListWithoutRoleIdListByIdList(List<UserId> userIdList) {
        return userMapper.selectByIds(UserId.toLong(userIdList));
    }

    @Override
    public Optional<User> findUserByUsername(@Nonnull Username username) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityByUsername(username)));
    }

    @Override
    public Optional<User> findUserByEmail(@Nonnull Email email) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityByEmail(email)));
    }

    @Override
    public Optional<User> findUserById(@Nonnull UserId userId) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityById(userId)));
    }

    @Override
    public List<User> findUserListByIdList(@Nonnull List<UserId> userIdList) {
        return userConverter.toDomain(findUserEntityListByIdList(userIdList));
    }

    @Override
    public Optional<User> findUserWithoutRoleIdListByUsername(@Nonnull Username username) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityWithoutRoleIdListByUsername(username)));
    }

    @Override
    public Optional<User> findUserWithoutRoleIdListByEmail(@Nonnull Email email) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityWithoutRoleIdListByEmail(email)));
    }

    @Override
    public Optional<User> findUserWithoutRoleIdListById(@NotNull UserId userId) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityWithoutRoleIdListById(userId)));
    }

    @Override
    public List<User> findUserListWithoutRoleIdListByIdList(@Nonnull List<UserId> userIdList) {
        return userConverter.toDomain(findUserEntityListWithoutRoleIdListByIdList(userIdList));
    }

    @Override
    public boolean existsByUsername(@Nonnull Username username) {
        return userMapper.selectCount(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username.getUsername())) > 0;
    }

    @Override
    public boolean existsByEmail(@Nonnull Email email) {
        return userMapper.selectCount(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getEmail, email.getEmail())) > 0;
    }

    @Transactional
    @Override
    public User save(@Nonnull User user) {
        try {
            UserEntity userEntity = userConverter.toEntity(user);

            userMapper.insertOrUpdate(userEntity);

            return userConverter.toDomain(userEntity);
        } catch (Exception exception) {
            throw new RuntimeException("保存User是发生错误", exception);
        }
    }
}
