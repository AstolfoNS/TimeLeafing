package com.astolfo;

import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoleMapperTest {

    @Resource
    RoleMapper roleMapper;

    @Test
    public void testRoleMapper() {
        List<RoleEntity> roleEntityList = roleMapper.selectRoleEntityListByIdList(List.of(1L, 2L));

        System.out.println(roleEntityList);
    }

}
