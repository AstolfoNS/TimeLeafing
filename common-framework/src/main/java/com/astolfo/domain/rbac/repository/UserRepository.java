package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.model.valueobject.entity.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(Email email);

    Optional<User> findUserById(Long id);

    User save(User user);

}
