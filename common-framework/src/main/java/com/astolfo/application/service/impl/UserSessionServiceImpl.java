package com.astolfo.application.service.impl;

import com.astolfo.application.service.UserSessionService;
import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.util.component.RedisCacheUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    public void cacheLoginUser(LoginUser loginUser) {
        redisCacheUtil.set(RedisCacheConstant.Login_USER_PERFIX.concat(loginUser.getStringId()), loginUser);
    }

    @Override
    public void cacheDeleteLoginUser(Long userId) {
        redisCacheUtil.delete(RedisCacheConstant.Login_USER_PERFIX.concat(userId.toString()));
    }
}
