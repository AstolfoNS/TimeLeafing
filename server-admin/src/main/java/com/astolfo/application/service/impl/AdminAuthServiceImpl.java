package com.astolfo.application.service.impl;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.webinterface.vo.TokenResponse;
import org.springframework.stereotype.Service;

@Service("AdminAuthService")
public class AdminAuthServiceImpl implements AuthService {

    @Override
    public ResponseResult<TokenResponse> login(LoginRequest loginRequest) {
        // TODO: login

        return null;
    }

    @Override
    public ResponseResult<LogoutResponse> logout() {
        // TODO: logout

        return null;
    }
}
