package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.converter.UserConverter;
import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.persistence.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;


    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.of(userConverter.toDomain(userMapper.findUserEntityByUsername(username)));
    }

    @Override
    public Optional<User> findUserByEmailAddress(String emailAddress) {
        return Optional.of(userConverter.toDomain(userMapper.findUserEntityByEmailAddress(emailAddress)));
    }

    @Override
    public Optional<User> findUserByUsernameOrEmailAddress(String usernameOrEmail) {
        Optional<User> userOptional = findUserByUsername(usernameOrEmail);

        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            return findUserByEmailAddress(usernameOrEmail);
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

}
