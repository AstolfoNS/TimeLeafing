package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.*;
import com.astolfo.infrastructure.persistence.converter.UserConverter;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User toDomain(UserEntity userEntity) {
        User.Details details = new User.Details();

        // 转化
        details.setId(UserId.of(userEntity.getId()));

        details.setEmail(Email.of(userEntity.getEmail()));

        details.setUsername(Username.of(userEntity.getUsername()));

        details.setNickname(Nickname.of(userEntity.getNickname()));

        details.setPasswordHash(PasswordHash.of(userEntity.getPassword()));

        details.setAvatar(Avatar.of(userEntity.getAvatar()));

        details.setGender(Gender.of(userEntity.getGender()));

        details.setIntroduction(userEntity.getIntroduction());

        details.setLastLoginTime(userEntity.getLastLoginTime());

        details.setRoleIdList(RoleId.toId(userEntity.getRoleIdList()));

        details.setEnabled(userEntity.getEnabled());

        details.setUpdateTime(userEntity.getUpdateTime());

        details.setCreateTime(userEntity.getCreateTime());

        details.setIsDeleted(userEntity.getIsDeleted());

        // 创建
        return User.of(details);
    }

    @Override
    public List<User> toDomain(List<UserEntity> userEntityList) {
        if (Objects.isNull(userEntityList)) {
            return List.of();
        } else {
            return userEntityList
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        // 转化
        userEntity.setId(user.getIdLong());

        userEntity.setEmail(user.getEmailString());

        userEntity.setUsername(user.getUsernameString());

        userEntity.setNickname(user.getNicknameString());

        userEntity.setPassword(user.getPasswordHashString());

        userEntity.setAvatar(user.getAvatarString());

        userEntity.setGender(user.getGenderString());

        userEntity.setIntroduction(user.getIntroduction());

        userEntity.setLastLoginTime(user.getLastLoginTime());

        userEntity.setRoleIdList(RoleId.toLong(user.getRoleIdList()));

        userEntity.setEnabled(user.getEnabled());

        userEntity.setUpdateTime(user.getUpdateTime());

        userEntity.setCreateTime(user.getCreateTime());

        userEntity.setIsDeleted(user.getIsDeleted());

        // 输出
        return userEntity;
    }

    @Override
    public List<UserEntity> toEntity(List<User> userList) {
        if (Objects.isNull(userList)) {
            return List.of();
        } else {
            return userList
                    .stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
        }
    }
}
