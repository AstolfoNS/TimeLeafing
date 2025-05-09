package com.astolfo.v1.mapper;

import com.astolfo.v1.mapper.provider.UserSqlProvider;
import com.astolfo.v1.model.entity.User;
import com.astolfo.v1.model.vo.RoleVO;
import com.astolfo.v1.model.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @SelectProvider(type = UserSqlProvider.class, method = "getUserById")
    UserVO getUserVOById(@Param("id") Long id);

    @SelectProvider(type = UserSqlProvider.class, method = "getUserByUsername")
    UserVO getUserVOByUsername(@Param("username") String username);

    @SelectProvider(type = UserSqlProvider.class, method = "getRoleVOsById")
    List<RoleVO> getRoleVOsById(@Param("id") Long id);

}
