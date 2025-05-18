package com.astolfo.application.service;

import com.astolfo.application.dto.UpdateUserInfoRequest;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.webinterface.vo.UserInfo;

public interface UserService {

    ResponseResult<UserInfo> getUserInfo();

    ResponseResult<UserInfo> getUserInfo(Long id);

    ResponseResult<UserInfo> getUserInfo(String username);

    ResponseResult<Void> updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest);

}
