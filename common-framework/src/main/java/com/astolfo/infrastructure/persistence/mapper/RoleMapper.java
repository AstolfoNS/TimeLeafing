package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {


    RoleEntity selectRoleEntityById(@Param("id") Long Id);

    RoleEntity selectRoleEntityByName(@Param("name") String name);

    List<RoleEntity> selectRoleEntityListByIdList(@Param("idList") List<Long> idList);

}
