package com.astolfo.webinterface.controller;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.webinterface.vo.LogoutResponse;
import com.astolfo.application.service.AuthService;
import com.astolfo.application.service.impl.GuestAuthServiceImpl;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.TokenResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/guest-auth")
@RestController
public class AuthController {

    @Resource(name = "GuestAuthService")
    private AuthService authService;

    
    @PostMapping("/login")
    public ResponseResult<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseResult<LogoutResponse> logout() {
        return authService.logout();
    }

}
