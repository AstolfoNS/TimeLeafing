package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RolePermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

//    @Select("""
//        SELECT
//            role_permission.permission_id
//        FROM
//            role_permission
//        WHERE
//            role_permission.role_id = #{id}
//    """)
    List<Long> selectPermissionIdByRoleId(@Param("id") Long id);

//    @Select("""
//        SELECT
//            role_permission.role_id
//        FROM
//            role_permission
//        WHERE
//            role_permission.permission_id = #{id}
//    """)
    List<Long> selectRoleIdByPermissionId(@Param("id") Long id);
}
