package com.astolfo.webinterface.controller;

import com.astolfo.application.dto.UserProfileRequest;
import com.astolfo.application.service.UserService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserProfile;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/profile")
    public ResponseResult<UserProfile> getUserProfile() {
        return userService.getUserProfile();
    }

    @PostMapping("/update-profile")
    public ResponseResult<Void> updateUserInfo(@RequestBody UserProfileRequest userProfileRequest) {
        return userService.updateUserProfile(userProfileRequest);
    }

}
