package com.astolfo.application.service;

import com.astolfo.application.dto.UserProfileRequest;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserProfile;

public interface UserProfileService {

    ResponseResult<UserProfile> getUserProfile();

    ResponseResult<UserProfile> getUserProfileById(UserId userId);

    ResponseResult<UserProfile> getUserProfileByEmail(Email email);

    ResponseResult<UserProfile> getUserProfileByUsername(Username username);

    ResponseResult<Void> updateUserProfile(UserProfileRequest userProfileRequest);

}
