package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.model.valueobject.entity.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(Email email);

    Optional<User> findById(Long id);

    User save(User user);

}
