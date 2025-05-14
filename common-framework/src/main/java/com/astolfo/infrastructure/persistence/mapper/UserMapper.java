package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("""
        SELECT
            user.*
        FROM
            user
        WHERE
            user.id = #{id}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "roleIdList",
                    column = "id",
                    many = @Many(select = "com.astolfo.infrastructure.persistence.mapper.UserRoleMapper.selectRoleIdByUserId")
            )
    })
    UserEntity selectUserEntityById(@Param("id") Long id);

    @Select("""
        SELECT
            user.*
        FROM
            user
        WHERE
            user.username = #{username}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "roleIdList",
                    column = "id",
                    many = @Many(select = "com.astolfo.infrastructure.persistence.mapper.UserRoleMapper.selectRoleIdByUserId")
            )
    })
    UserEntity selectUserEntityByUsername(@Param("username") String username);

}
