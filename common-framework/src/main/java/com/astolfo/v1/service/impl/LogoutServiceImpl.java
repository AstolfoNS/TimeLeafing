package com.astolfo.v1.service.impl;

import com.astolfo.v1.common.constants.RedisCacheConstant;
import com.astolfo.v1.common.enums.HttpCode;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.common.utils.components.RedisCacheUtil;
import com.astolfo.v1.security.entity.LoginUser;
import com.astolfo.v1.service.LogoutService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    public ResponseResult<Void> logout() {
        try {
            LoginUser loginUser = (LoginUser)  SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            redisCacheUtil.delete(RedisCacheConstant.Login_USER_PERFIX.concat(loginUser.getStringId()));

            return ResponseResult.okResult(HttpCode.LOGOUT_SUCCESS);
        } catch (RuntimeException exception) {
            return ResponseResult.errorResult(HttpCode.LOGOUT_FAILED);
        }
    }
}
