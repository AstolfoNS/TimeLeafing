package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity selectUserEntityByUsername(@Param("username") String username);

    UserEntity selectUserEntityByEmail(@Param("email") String email);

    UserEntity selectUserEntityById(@Param("id") Long id);

    List<UserEntity> selectUserEntityListByIdList(List<Long> idList);

}
