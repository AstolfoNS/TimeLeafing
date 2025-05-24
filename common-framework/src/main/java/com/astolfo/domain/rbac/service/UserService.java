package com.astolfo.domain.rbac.service;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;

public interface UserService {

    User findUserById(UserId userId) throws RuntimeException;

    User findUserByEmail(Email email) throws RuntimeException;

    User findUserByUsername(Username username) throws RuntimeException;

    User findUserWithoutRoleIdListById(UserId userId) throws RuntimeException;

    User findUserWithoutRoleIdListByEmail(Email email) throws RuntimeException;

    User findUserWithoutRoleIdListByUsername(Username username) throws RuntimeException;
}
