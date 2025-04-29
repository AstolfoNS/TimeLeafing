package com.astolfo.mapper;

import com.astolfo.mapper.provider.UserSqlProvider;
import com.astolfo.model.entity.User;
import com.astolfo.model.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @SelectProvider(type = UserSqlProvider.class, method = "getUserById")
    UserVO getUserVOById(@Param("id") Long id);

    @SelectProvider(type = UserSqlProvider.class, method = "getUserByUsername")
    UserVO getUserVOByUsername(@Param("username") String username);

}
