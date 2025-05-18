package com.astolfo.webinterface.controller;

import com.astolfo.application.service.UserService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/access/user")
@RestController
public class AccessUserController {

    @Resource
    UserService userService;


    @GetMapping("/info")
    public ResponseResult<UserInfo> getUserInfo(@RequestParam("id") Long id) {
        return userService.getUserInfo(id);
    }

    @GetMapping("/{username}/info")
    public ResponseResult<UserInfo> getUserInfo(@PathVariable("username") String username) {
        return userService.getUserInfo(username);
    }

}
