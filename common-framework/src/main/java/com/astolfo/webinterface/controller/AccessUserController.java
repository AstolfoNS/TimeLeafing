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
    UserProfileService userProfileService;


    @GetMapping("/profile/id/{userId}")
    public ResponseResult<UserProfile> getUserProfileById(@PathVariable("userId") Long userId) {
        return userProfileService.getUserProfileById(UserId.of(userId));
    }

    @GetMapping("/profile/email/{email}")
    public ResponseResult<UserProfile> getUserProfileByEmail(@PathVariable("email") String email) {
        return userProfileService.getUserProfileByEmail(Email.of(email));
    }

    @GetMapping("/profile/username/{username}")
    public ResponseResult<UserProfile> getUserProfileByUsername(@PathVariable("username") String username) {
        return userProfileService.getUserProfileByUsername(Username.of(username));
    }

}
