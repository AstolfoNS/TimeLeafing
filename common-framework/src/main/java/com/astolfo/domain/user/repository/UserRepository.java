package com.astolfo.domain.user.repository;

import com.astolfo.domain.user.model.User;
import com.astolfo.domain.user.model.valueobject.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(Email email);

    Optional<User> findById(Long id);

    User save(User user);

}
