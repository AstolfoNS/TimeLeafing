package com.astolfo;

import com.astolfo.domain.rbac.model.root.Permission;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.service.UserPermissionService;
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
        List<Permission> permissionList = userPermissionService.findUserPermissionListById(UserId.of(1L));

        System.out.println(permissionList);
    }
}
