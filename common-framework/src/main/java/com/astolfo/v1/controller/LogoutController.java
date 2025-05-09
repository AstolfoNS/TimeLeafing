package com.astolfo.v1.controller;

import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.service.LogoutService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Resource
    private LogoutService logoutService;


    @GetMapping("/logout")
    public ResponseResult<Void> logout() {
        return logoutService.logout();
    }
}
