package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.domain.rbac.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Override
    public Optional<Role> findRoleById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Optional<Role>> findRoleListByIdList(List<Long> idList) {
        return List.of();
    }

    @Override
    public Role save(Role role) {
        return null;
    }
}
