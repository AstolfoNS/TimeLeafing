package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.rbac.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return Optional.empty();
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
