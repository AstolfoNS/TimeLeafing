package com.astolfo.application.update;

import com.astolfo.application.dto.UpdateUserInfoRequest;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.enumtype.Gender;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserInfoRequestUpdate {

    public User toUser(UpdateUserInfoRequest updateUserInfoRequest, User user) {
        user.setNickname(updateUserInfoRequest.getNickname());

        user.setGender(Gender.of(updateUserInfoRequest.getGender()));

        user.setIntroduction(updateUserInfoRequest.getIntroduction());

        return user;
    }
}
