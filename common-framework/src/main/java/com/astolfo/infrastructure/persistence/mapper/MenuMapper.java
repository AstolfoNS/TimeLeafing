package com.astolfo.infrastructure.persistence.mapper;

import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import com.astolfo.infrastructure.persistence.mapper.provider.MenuSqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {

    @SelectProvider(type = MenuSqlProvider.class, method = "findUserMenuEntityListByUsername")
    List<MenuEntity> findUserMenuEntityListByUsername(@Param("username") String Username);

    @SelectProvider(type = MenuSqlProvider.class, method = "findUserMenuEntityListById")
    List<MenuEntity> findUserMenuEntityListById(@Param("id") Long id);
}
