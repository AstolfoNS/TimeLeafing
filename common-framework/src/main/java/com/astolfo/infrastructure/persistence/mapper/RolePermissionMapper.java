package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RolePermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

    List<Long> selectPermissionIdByRoleId(@Param("id") Long id);

    List<Long> selectRoleIdByPermissionId(@Param("id") Long id);
}
