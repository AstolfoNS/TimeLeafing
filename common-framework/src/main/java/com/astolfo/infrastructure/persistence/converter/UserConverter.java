package com.astolfo.infrastructure.persistence.converter;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public interface UserConverter {

    User toDomain(UserEntity userEntity);

    List<User> toDomain(List<UserEntity> userEntityList);

    UserEntity toEntity(User user);

    List<UserEntity> toEntity(List<User> userList);

}
