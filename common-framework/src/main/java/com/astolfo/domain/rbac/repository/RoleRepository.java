package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByName(String name);

    List<Optional<Role>> findRoleListByIdList(List<Long> idList);

    Role save(Role role);

}
