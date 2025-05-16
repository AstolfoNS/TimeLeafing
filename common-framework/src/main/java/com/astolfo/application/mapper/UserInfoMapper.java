package com.astolfo.application.mapper;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.webinterface.vo.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserInfoMapper {

    @Mapping(source = "email.email", target = "email")
    @Mapping(source = "gender.gender", target = "gender")
    UserInfo userToUserInfo(User user);

}
