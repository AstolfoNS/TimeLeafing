package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.model.valueobject.entity.Email;
import com.astolfo.domain.domain.rbac.model.valueobject.enumtype.Gender;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class UserConverter {

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        } else {
            User user = new User();

            user.setId(userEntity.getId());

            user.setEmail(Email.of(userEntity.getEmail()));

            user.setUsername(userEntity.getUsername());

            user.setNickname(userEntity.getNickname());

            user.setPassword(userEntity.getPassword());

            user.setPassword(userEntity.getPassword());

            user.setAvatar(userEntity.getAvatar());

            user.setGender(Gender.of(userEntity.getGender()));

            user.setIntroduction(userEntity.getIntroduction());

            user.setLastLoginTime(userEntity.getLastLoginTime());

            user.setRoleIdList(userEntity.getRoleIdList());

            user.setEnabled(userEntity.getEnabled());

            user.setCreateTime(userEntity.getCreateTime());

            user.setUpdateTime(userEntity.getUpdateTime());

            user.setIsDeleted(userEntity.getIsDeleted());

            return user;
        }
    }

    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());

        userEntity.setEmail(user.getEmail().getEmail());

        userEntity.setUsername(user.getUsername());

        userEntity.setNickname(user.getNickname());

        userEntity.setPassword(user.getPassword());

        userEntity.setAvatar(user.getAvatar());

        userEntity.setGender(user.getGender().getGender());

        userEntity.setIntroduction(user.getIntroduction());

        userEntity.setLastLoginTime(user.getLastLoginTime());

        userEntity.setRoleIdList(user.getRoleIdList());

        userEntity.setEnabled(user.getEnabled());

        userEntity.setCreateTime(user.getCreateTime());

        userEntity.setUpdateTime(user.getUpdateTime());

        userEntity.setIsDeleted(user.getIsDeleted());

        return userEntity;
    }

    public List<User> toDomain(List<UserEntity> userEntityList) {
        if (userEntityList == null) {
            return null;
        } else {
            return userEntityList
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        }
    }

    public List<UserEntity> toEntity(List<User> userList) {
        if (userList == null) {
            return null;
        } else {
            return userList
                    .stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
    }
}
