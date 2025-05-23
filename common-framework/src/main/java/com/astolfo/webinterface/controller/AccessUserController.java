package com.astolfo.webinterface.controller;

import com.astolfo.application.service.UserService;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserProfile;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/access/user")
@RestController
public class AccessUserController {

    @Resource
    UserService userService;


    @GetMapping("/info")
    public ResponseResult<UserProfile> getUserInfo(@RequestParam("id") Long userId) {
        return userService.getUserInfo(UserId.of(userId));
    }

    @GetMapping("/{username}/info")
    public ResponseResult<UserProfile> getUserInfo(@PathVariable("username") String username) {
        return userService.getUserInfo(Username.of(username));
    }

}
