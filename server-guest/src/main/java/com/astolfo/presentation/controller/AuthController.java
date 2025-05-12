package com.astolfo.presentation.controller;

import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.presentation.dto.LoginRequest;
import com.astolfo.presentation.vo.LoginResponse;
import com.astolfo.presentation.vo.LogoutResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/guest-auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    
    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/logout")
    public ResponseResult<LogoutResponse> login() {
        return authService.logout();
    }

}
