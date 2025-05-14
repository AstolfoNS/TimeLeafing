package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findUserByUsername(@Nonnull String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(@Nonnull Email email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(@Nonnull Long id) {
        return Optional.empty();
    }

    @Override
    public User save(@Nonnull User user) {
        return null;
    }
}
