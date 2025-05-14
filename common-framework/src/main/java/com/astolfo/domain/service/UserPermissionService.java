package com.astolfo.domain.service;

import com.astolfo.domain.domain.rbac.model.Permission;

import java.util.List;

public interface UserPermissionService {

    List<Permission> findUserPermissionListById(Long id);

    List<Permission> findUserPermissionListByUsername(String username);

}
