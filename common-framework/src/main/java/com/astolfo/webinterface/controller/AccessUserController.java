package com.astolfo.webinterface.controller;

import com.astolfo.application.service.UserProfileService;
import com.astolfo.domain.rbac.model.valueobject.Email;
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
    UserProfileService userService;


    @GetMapping("/profile")
    public ResponseResult<UserProfile> getUserProfileById(@RequestParam("id") Long userId) {
        return userService.getUserProfileById(UserId.of(userId));
    }

    @GetMapping("/profile")
    public ResponseResult<UserProfile> getUserProfileByEmail(String email) {
        return userService.getUserProfileByEmail(Email.of(email));
    }

    @GetMapping("/profile")
    public ResponseResult<UserProfile> getUserProfileByUsername(@RequestParam("username") String username) {
        return userService.getUserProfileByUsername(Username.of(username));
    }

}
