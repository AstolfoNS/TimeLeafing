package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleRepository {

    List<Role> findRoleByUserId(Long userId);

    List<Role> findRoleByUsername(String username);

}
