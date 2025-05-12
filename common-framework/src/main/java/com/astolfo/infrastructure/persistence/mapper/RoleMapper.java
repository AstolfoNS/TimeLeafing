package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    List<RoleEntity> findUserRoleEntityListByUsername(@Param("username") String username);

    List<RoleEntity> findUserMenuEntityListById(@Param("id") String id);

}
