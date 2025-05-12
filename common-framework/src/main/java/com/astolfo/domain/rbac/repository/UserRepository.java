package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmailAddress(String emailAddress);

    Optional<User> findUserByUsernameOrEmailAddress(String usernameOrEmailAddress);

    Optional<User> findUserById(Long id);

    User save(User user);

}
