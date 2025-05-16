package com.astolfo.application.service.impl;

import com.astolfo.application.mapper.UserInfoMapper;
import com.astolfo.application.service.UserService;
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

    @Override
    public ResponseResult<UserInfo> getUserInfo() {
        return ResponseResult.okResult(userInfoMapper.userToUserInfo(SecurityUtil.getRequiredCurrentUser()));
    }

    @Override
    public ResponseResult<UserInfo> getUserInfo(String username) {
        try {
            return ResponseResult.okResult(userInfoMapper.userToUserInfo(userRepository.findUserByUsername(username).orElse(null)));
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_NOT_EXIST);
        }
    }
}
