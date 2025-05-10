package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> getRoleByUserId(Long userId);

    List<Role> getRoleByUsername(String username);

}
