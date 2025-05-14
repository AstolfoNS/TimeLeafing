package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.persistence.converter.UserConverter;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.astolfo.infrastructure.persistence.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;


    private UserEntity findUserEntityByUsername(String username) {
        return userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
    }

    private UserEntity findUserEntityByEmail(Email email) {
        return userMapper.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getEmail, email.getEmail()));
    }

    private UserEntity findUserEntityById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Optional<User> findUserByUsername(@Nonnull String username) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityByUsername(username)));
    }

    @Override
    public Optional<User> findUserByEmail(@Nonnull Email email) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityByEmail(email)));
    }

    @Override
    public Optional<User> findUserById(@Nonnull Long id) {
        return Optional.ofNullable(userConverter.toDomain(findUserEntityById(id)));
    }

    @Override
    public User save(@Nonnull User user) {
        return null;
    }
}
