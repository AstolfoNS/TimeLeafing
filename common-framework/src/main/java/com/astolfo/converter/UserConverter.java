package com.astolfo.converter;

import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.rbac.model.valueobject.enumtype.Gender;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter {

    public Optional<User> toDomain(UserEntity userEntity) {
        return Optional.of(new User(
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
                )
        );
    }

    public Optional<UserEntity> toEntity(User user) {
        return Optional.of(new UserEntity(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getEmail().getEmailAddress(),
                        user.getAvatar(),
                        user.getGender().getGenderName(),
                        user.getIntroduction(),
                        user.getLastLoginTime(),
                        user.getEnabled(),
                        user.getCreateTime(),
                        user.getUpdateTime(),
                        user.getIsDeleted()
                )
        );
    }

}
