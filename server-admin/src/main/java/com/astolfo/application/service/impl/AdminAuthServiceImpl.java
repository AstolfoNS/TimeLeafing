package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.webinterface.vo.TokenResponse;

public class AdminAuthServiceImpl implements AuthService {

    @Override
    public ResponseResult<TokenResponse> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        return null;
    }
}
