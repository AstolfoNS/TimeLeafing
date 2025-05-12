package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.astolfo.infrastructure.persistence.mapper.provider.UserSqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @SelectProvider(type = UserSqlProvider.class, method = "findUserEntityByUsername")
    UserEntity findUserEntityByUsername(@Param("username") String username);

    @SelectProvider(type = UserSqlProvider.class, method = "findUserEntityByEmailAddress")
    UserEntity findUserEntityByEmailAddress(@Param("emailAddress") String emailAddress);

}
