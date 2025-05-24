package com.astolfo.application.service.impl;

import com.astolfo.application.dto.UserProfileRequest;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.application.service.UserProfileService;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.security.util.SecurityUtil;
import com.astolfo.webinterface.vo.UserProfile;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserRepository userRepository;


    private UserProfile toUserProfile(User user) {
        UserProfile userProfile = new UserProfile();

        userProfile.setId(user.getId().getUserId());

        userProfile.setEmail(user.getEmail().getEmail());

        userProfile.setUsername(user.getUsername().getUsername());

        userProfile.setNickname(user.getNickname().getNickname());

        userProfile.setAvatar(user.getAvatar().getAvatar());

        userProfile.setGender(user.getGender().getGender());

        userProfile.setIntroduction(user.getIntroduction());

        userProfile.setLastLoginTime(user.getLastLoginTime());

        return userProfile;
    }

    @Override
    public ResponseResult<UserProfile> getUserProfile() {
        try {
            UserId id = UserId.of(SecurityUtil.getRequiredCurrentUserId());

            User user = userRepository.findUserWithoutRoleIdListById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

            return ResponseResult.okResult(toUserProfile(user));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileById(UserId userId) {
        try {
            User user = userRepository.findUserWithoutRoleIdListById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

            return ResponseResult.okResult(toUserProfile(user));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByEmail(Email email) {
         try {
            User user = userRepository.findUserWithoutRoleIdListByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));

            return ResponseResult.okResult(toUserProfile(user));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByUsername(Username username) {
        try {
            User user = userRepository.findUserWithoutRoleIdListByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));

            return ResponseResult.okResult(toUserProfile(user));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<Void> updateUserProfile(UserProfileRequest userProfileRequest) {
        // TODO: updateUserProfile
        return null;
    }
}
