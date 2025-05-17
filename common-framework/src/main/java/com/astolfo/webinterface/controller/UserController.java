package com.astolfo.webinterface.controller;

import com.astolfo.application.dto.UpdateUserInfoRequest;
import com.astolfo.application.service.UserService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/info")
    public ResponseResult<UserInfo> getUserInfo() {
        return userService.getUserInfo();
    }

    public ResponseResult<Void> updateUserInfo(@RequestBody UpdateUserInfoRequest updateUserInfoRequest) {
        return userService.updateUserInfo(updateUserInfoRequest);
    }

}
