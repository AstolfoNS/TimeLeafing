package com.astolfo.webinterface.mapper;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.webinterface.vo.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    public UserInfo userToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(user.getId());

        userInfo.setEmail(user.getEmail().getEmail());

        userInfo.setUsername(user.getUsername());

        userInfo.setNickname(user.getNickname());

        userInfo.setAvatar(user.getAvatar());

        userInfo.setGender(user.getGender().getGender());

        userInfo.setIntroduction(user.getIntroduction());

        userInfo.setLastLoginTime(user.getLastLoginTime());

        return userInfo;
    }

}
