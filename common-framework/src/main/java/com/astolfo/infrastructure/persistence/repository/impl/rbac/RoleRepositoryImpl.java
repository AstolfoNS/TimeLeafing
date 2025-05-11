package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.domain.rbac.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public List<Role> findRoleListByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Role> findRoleListByUserUsername(String username) {
        return List.of();
    }

}
