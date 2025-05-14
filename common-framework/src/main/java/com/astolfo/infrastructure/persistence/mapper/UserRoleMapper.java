package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    @Select("""
        SELECT
            user_role.role_id
        FROM
            user_role
        WHERE
            user_role.user_id = #{userId}
    """)
    List<Long> selectRoleIdByUserId(@Param("userId") Long userId);

    @Select("""
        SELECT
            user_role.user_id
        FROM
            user_role
        WHERE
            user_role.role_id = #{roleId}
    """)
    List<Long> selectUserIdByRoleId(@Param("roleId") Long roleId);
}
