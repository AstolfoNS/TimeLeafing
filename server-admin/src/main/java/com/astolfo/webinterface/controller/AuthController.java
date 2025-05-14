package com.astolfo.webinterface.controller;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.application.service.AuthService;
import com.astolfo.application.service.impl.AdminAuthServiceImpl;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.webinterface.vo.TokenResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin-auth")
@RestController
public class AuthController {

    @Resource(type = AdminAuthServiceImpl.class)
    private AuthService authService;


    public ResponseResult<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    public ResponseResult<LogoutResponse> logout() {
        return authService.logout();
    }

}
