package com.astolfo.application.service.impl;

import com.astolfo.application.dto.UserProfileRequest;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.application.service.UserService;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public ResponseResult<UserProfile> getUserProfile() {
        return null;
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileById(UserId userId) {
        return null;
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByEmail(Email email) {
        return null;
    }

    @Override
    public ResponseResult<UserProfile> getUserProfileByUsername(Username username) {
        return null;
    }

    @Override
    public ResponseResult<Void> updateUserProfile(UserProfileRequest userProfileRequest) {
        return null;
    }
}
