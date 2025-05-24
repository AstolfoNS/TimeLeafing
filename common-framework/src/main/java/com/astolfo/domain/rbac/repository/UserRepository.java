package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import jakarta.annotation.Nonnull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(@Nonnull Username username);

    Optional<User> findUserByEmail(@Nonnull Email email);

    Optional<User> findUserById(@Nonnull UserId userId);

    List<User> findUserListByIdList(@Nonnull List<UserId> userIdList);

    Optional<User> findUserWithoutRoleIdListByUsername(@Nonnull Username username);

    Optional<User> findUserWithoutRoleIdListByEmail(@Nonnull Email email);

    Optional<User> findUserWithoutRoleIdListById(@Nonnull UserId userId);

    List<User> findUserListWithoutRoleIdListByIdList(@Nonnull List<UserId> userIdList);

    User save(@Nonnull User user);

}
