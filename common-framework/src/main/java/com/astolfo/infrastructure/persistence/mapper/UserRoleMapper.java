package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.UserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

//    @Select("""
//        SELECT
//            user_role.role_id
//        FROM
//            user_role
//        WHERE
//            user_role.user_id = #{id}
//    """)
    List<Long> selectRoleIdByUserId(@Param("id") Long id);

//    @Select("""
//        SELECT
//            user_role.user_id
//        FROM
//            user_role
//        WHERE
//            user_role.role_id = #{id}
//    """)
    List<Long> selectUserIdByRoleId(@Param("id") Long id);
}
