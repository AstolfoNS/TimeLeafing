package com.astolfo;

import com.astolfo.domain.rbac.model.root.Role;
import com.astolfo.domain.rbac.repository.RoleRepository;
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

    @Resource
    RoleRepository roleRepository;


    @Test
    public void testRoleMapper() {

        List<Role> roleList = roleRepository.findRoleListByIdList(List.of());

        System.out.println(roleList);
    }

}
