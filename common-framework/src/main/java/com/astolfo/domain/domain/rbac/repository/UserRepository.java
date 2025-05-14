package com.astolfo.domain.domain.rbac.repository;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import jakarta.annotation.Nonnull;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(@Nonnull String username);

    Optional<User> findUserByEmail(@Nonnull Email email);

    Optional<User> findUserById(@Nonnull Long id);

    User save(@Nonnull User user);

}
