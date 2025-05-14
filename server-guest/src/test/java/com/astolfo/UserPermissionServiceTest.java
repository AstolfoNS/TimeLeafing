package com.astolfo;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.service.UserPermissionService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserPermissionServiceTest {

    @Resource
    private UserPermissionService userPermissionService;

    @Test
    public void USerPermissionServiceTest() {
        List<Permission> permissionList = userPermissionService.findUserPermissionListById(1L);

        System.out.println(permissionList);
    }
}
