package com.astolfo.application.service;

import com.astolfo.infrastructure.security.userdetails.LoginUser;

public interface UserSessionService {

    void cacheLoginUser(LoginUser loginUser);

    void cacheDeleteLoginUser(Long userId);

}
