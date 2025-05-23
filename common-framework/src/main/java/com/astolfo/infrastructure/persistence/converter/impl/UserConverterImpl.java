package com.astolfo.infrastructure.persistence.converter.impl;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.Gender;
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
        // TODO: toDomain

        return null;
    }

    @Override
    public List<User> toDomain(List<UserEntity> userEntityList) {
        // TODO: toDomain

        return List.of();
    }

    @Override
    public UserEntity toEntity(User user) {
        // TODO: toEntity

        return null;
    }

    @Override
    public List<UserEntity> toEntity(List<User> userList) {
        // TODO: toEntity

        return List.of();
    }
}
