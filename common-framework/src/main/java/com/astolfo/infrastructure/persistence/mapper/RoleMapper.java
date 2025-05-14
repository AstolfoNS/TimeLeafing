package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    @Select("""
        SELECT
            role.*
        FROM
            role
        WHERE
            role.id = #{id}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "permissionIdList",
                    column = "id",
                    many = @Many(select = "com.astolfo.infrastructure.persistence.mapper.RolePermissionMapper.selectPermissionIdByRoleId")
            )
    })
    RoleEntity selectRoleEntityById(@Param("id") Long Id);

    @Select("""
        SELECT
            role.*
        FROM
            role
        WHERE
            role.name = #{name}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "permissionIdList",
                    column = "id",
                    many = @Many(select = "com.astolfo.infrastructure.persistence.mapper.RolePermissionMapper.selectPermissionIdByRoleId")
            )
    })
    RoleEntity selectRoleEntityByName(@Param("name") String name);

    @Select("""
        SELECT
            role.*
        FROM
            role
        WHERE
            role.id IN ${idList}
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "permissionIdList",
                    column = "id",
                    many = @Many(select = "com.astolfo.infrastructure.persistence.mapper.RolePermissionMapper.selectPermissionIdByRoleId")
            )
    })
    List<RoleEntity> findRoleEntityListByIdList(List<Long> idList);

}
