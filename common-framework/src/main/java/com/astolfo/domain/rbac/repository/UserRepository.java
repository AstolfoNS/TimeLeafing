package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUserUsername(String username);

    Optional<User> findUserByUserId(Long id);

    User save(User user);

}
