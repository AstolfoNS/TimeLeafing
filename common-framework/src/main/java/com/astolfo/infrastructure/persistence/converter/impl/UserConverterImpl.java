package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.*;
import com.astolfo.infrastructure.persistence.converter.UserConverter;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User toDomain(UserEntity userEntity) {
        User.Details details = new User.Details();

        details.setId(UserId.of(userEntity.getId()));

        details.setEmail(Email.of(userEntity.getEmail()));

        details.setUsername(Username.of(userEntity.getUsername()));

        details.setNickname(Nickname.of(userEntity.getNickname()));

        details.setPasswordHash(PasswordHash.of(userEntity.getPassword()));

        details.setAvatar(Avatar.of(userEntity.getAvatar()));

        details.setGender(Gender.of(userEntity.getGender()));

        details.setIntroduction(userEntity.getIntroduction());

        details.setLastLoginTime(userEntity.getLastLoginTime());

        details.setRoleIdList(
                userEntity
                        .getRoleIdList()
                        .stream()
                        .map(RoleId::of)
                        .collect(Collectors.toList())
        );

        details.setEnabled(userEntity.getEnabled());

        details.setUpdateTime(userEntity.getUpdateTime());

        details.setCreateTime(userEntity.getCreateTime());

        details.setIsDeleted(userEntity.getIsDeleted());

        return User.of(details);
    }

    @Override
    public List<User> toDomain(List<UserEntity> userEntityList) {
        return userEntityList
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId().getUserId());

        userEntity.setEmail(user.getEmail().getEmail());

        userEntity.setUsername(user.getUsername().getUsername());

        userEntity.setNickname(user.getNickname().getNickname());

        userEntity.setPassword(user.getPasswordHash().getPasswordHash());

        userEntity.setAvatar(user.getAvatar().getAvatar());

        userEntity.setGender(user.getGender().getGender());

        userEntity.setIntroduction(user.getIntroduction());

        userEntity.setLastLoginTime(user.getLastLoginTime());

        userEntity.setRoleIdList(
                user
                        .getRoleIdList()
                        .stream()
                        .map(RoleId::getRoleId)
                        .collect(Collectors.toList())
        );

        userEntity.setEnabled(user.getEnabled());

        userEntity.setUpdateTime(user.getUpdateTime());

        userEntity.setCreateTime(user.getCreateTime());

        userEntity.setIsDeleted(user.getIsDeleted());

        return userEntity;
    }

    @Override
    public List<UserEntity> toEntity(List<User> userList) {
        return userList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
