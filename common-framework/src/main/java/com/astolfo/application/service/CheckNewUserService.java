package com.astolfo.application.service;

import com.astolfo.domain.rbac.model.root.User;

public interface CheckNewUserService {

    boolean checkNewUser(User.NewUser newUser);
}
