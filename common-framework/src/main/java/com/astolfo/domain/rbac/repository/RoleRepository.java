package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> findUserRoleListById(Long userId);

    List<Role> findUserRoleListByUsername(String username);

}
