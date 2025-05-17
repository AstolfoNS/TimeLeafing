package com.astolfo.application.service.impl;

import com.astolfo.application.dto.UpdateUserInfoRequest;
import com.astolfo.application.update.UpdateUserInfoRequestUpdate;
import com.astolfo.webinterface.mapper.UserInfoMapper;
import com.astolfo.application.service.UserService;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import com.astolfo.infrastructure.security.util.SecurityUtil;
import com.astolfo.webinterface.vo.UserInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    UpdateUserInfoRequestUpdate updateUserInfoRequestUpdate;


    @Override
    public ResponseResult<UserInfo> getUserInfo() {
        return ResponseResult.okResult(userInfoMapper.userToUserInfo(SecurityUtil.getRequiredCurrentUser()));
    }

    @Override
    public ResponseResult<UserInfo> getUserInfo(Long id) {
        try {
            return ResponseResult.okResult(userInfoMapper.userToUserInfo(userRepository.findUserById(id).orElseThrow()));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<UserInfo> getUserInfo(String username) {
        try {
            return ResponseResult.okResult(userInfoMapper.userToUserInfo(userRepository.findUserByUsername(username).orElseThrow()));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }

    @Override
    public ResponseResult<Void> updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        try {
            User user = userRepository.findUserById(SecurityUtil.getRequiredCurrentUserId()).orElseThrow();

            userRepository.save(updateUserInfoRequestUpdate.toUser(updateUserInfoRequest, user));

            return ResponseResult.okResult();
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_UPDATE_FAILED);
        }
    }

}
