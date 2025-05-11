package com.astolfo.converter;

import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.rbac.model.valueobject.enumtype.Gender;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        } else {
            return new User(
                    userEntity.getId(),
                    Email.of(userEntity.getEmail()),
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getAvatar(),
                    Gender.get(userEntity.getGender()),
                    userEntity.getIntroduction(),
                    userEntity.getLastLoginTime(),
                    userEntity.getEnabled(),
                    userEntity.getCreateTime(),
                    userEntity.getUpdateTime(),
                    userEntity.getIsDeleted()
            );
        }
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail().getEmailAddress(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getGender().getGenderName(),
                user.getIntroduction(),
                user.getLastLoginTime(),
                user.getEnabled(),
                user.getCreateTime(),
                user.getUpdateTime(),
                user.getIsDeleted()
        );
    }

}
