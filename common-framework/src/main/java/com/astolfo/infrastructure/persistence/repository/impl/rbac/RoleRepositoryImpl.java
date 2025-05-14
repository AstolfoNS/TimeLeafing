package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.domain.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.persistence.entity.RoleEntity;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Resource
    RoleMapper roleMapper;


    private RoleEntity findRoleEntityById(Long id) {
        return roleMapper.selectRoleEntityById(id);
    }

    private RoleEntity findRoleEntityByName(String name) {
        return roleMapper.selectRoleEntityByName(name);
    }

    private List<RoleEntity> findRoleEntityListByIdList(List<Long> idList) {
        return roleMapper.selectRoleEntityListByIdList(idList);
    }

    @Override
    public Optional<Role> findRoleById(@Nonnull Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findRoleByName(@Nonnull String name) {
        return Optional.empty();
    }

    @Override
    public List<Role> findRoleListByIdList(@Nonnull List<Long> idList) {
        return List.of();
    }

    @Override
    public Role save(@Nonnull Role role) {
        return null;
    }
}
