package com.astolfo.controller;

import com.astolfo.common.result.ResponseResult;
import com.astolfo.model.dto.UserDTO;
import com.astolfo.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<Void> login(@RequestBody UserDTO userDTO) {
        return loginService.login(userDTO);
    }

}
