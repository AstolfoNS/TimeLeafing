package com.astolfo.controller;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.service.LogoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    private LogoutService logoutService;


    @GetMapping("/logout")
    public ResponseResult<Void> logout() {
        return logoutService.logout();
    }
}
