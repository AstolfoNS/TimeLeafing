package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.astolfo.infrastructure.persistence.mapper.provider.RoleSqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    @SelectProvider(type = RoleSqlProvider.class, method = "findUserRoleEntityListByUsername")
    List<RoleEntity> findUserRoleEntityListByUsername(@Param("username") String username);

    @SelectProvider(type = RoleSqlProvider.class, method = "findUserMenuEntityListById")
    List<RoleEntity> findUserMenuEntityListById(@Param("id") Long id);

}
