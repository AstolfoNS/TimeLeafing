package com.astolfo.presentation.controller;

import com.astolfo.application.service.AuthService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.presentation.dto.LoginRequest;
import com.astolfo.presentation.vo.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/guest")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
