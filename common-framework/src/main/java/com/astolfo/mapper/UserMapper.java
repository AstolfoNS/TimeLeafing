package com.astolfo.mapper;

import com.astolfo.model.entity.User;
import com.astolfo.model.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("""
        SELECT
            user.*
        FROM
            user
        WHERE
            user.id = #{id}
    """)
    UserVO getUserVOById(@Param("id") Long id);

    @Select("""
        SELECT
            user.*
        FROM
            user
        WHERE
            user.username = #{username}
    """)
    UserVO getUserVOByUsername(@Param("username") String username);

}
