package com.astolfo.application.service.impl;

import com.astolfo.application.dto.UserProfileRequest;
import com.astolfo.application.service.AuthenticationService;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.*;
import com.astolfo.application.service.UserProfileService;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserService;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.PresignedUrl;
import com.astolfo.webinterface.vo.UserProfile;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationService authenticationService;


    private UserProfile toUserProfile(User user) {
        UserProfile userProfile = new UserProfile();

        userProfile.setId(Objects.isNull(user.getId()) ? null : user.getId().getUserId());

        userProfile.setEmail(Objects.isNull(user.getEmail()) ? null : user.getEmail().getEmail());

        userProfile.setUsername(Objects.isNull(user.getUsername()) ? null : user.getUsername().getUsername());

        userProfile.setNickname(Objects.isNull(user.getNickname()) ? null : user.getNickname().getNickname());

        userProfile.setAvatar(Objects.isNull(user.getAvatar()) ? null : user.getAvatar().getAvatar());

        userProfile.setGender(Objects.isNull(user.getGender()) ? null : user.getGender().getGender());

        userProfile.setIntroduction(user.getIntroduction());

        userProfile.setLastLoginTime(user.getLastLoginTime());

        return userProfile;
    }

    @Override
    public ResponseResult<UserProfile> getUserProfile() {
        try {
            return ResponseResult.okResult(toUserProfile(authenticationService.getCurrentUser()));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST.getCode(), exception.getMessage());
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileById(UserId userId) {
        try {
            return ResponseResult.okResult(toUserProfile(userService.findUserWithoutRoleIdListById(userId)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByEmail(Email email) {
         try {
            return ResponseResult.okResult(toUserProfile(userService.findUserWithoutRoleIdListByEmail(email)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByUsername(Username username) {
        try {
            return ResponseResult.okResult(toUserProfile(userService.findUserWithoutRoleIdListByUsername(username)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<Void> updateUserProfile(UserProfileRequest userProfileRequest) {
        try {
            User user = authenticationService.getCurrentUser();

            user.updateNickname(Nickname.of(userProfileRequest.getNickname()));

            user.updateGender(Gender.of(userProfileRequest.getGender()));

            user.updateIntroduction(userProfileRequest.getIntroduction());

            userRepository.save(user);

            return ResponseResult.okResult();
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_UPDATE_FAILED);
        }
    }

    @Override
    public ResponseResult<Void> updateUserPassword(PasswordHash passwordHash) {
        // TODO: update user password


        return null;
    }

    @Override
    public ResponseResult<PresignedUrl> updateUserAvatar(UserProfileRequest userProfileRequest) {
        // TODO: update user avatar


        return null;
    }
}
