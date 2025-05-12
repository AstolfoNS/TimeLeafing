package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.converter.RoleConverter;
import com.astolfo.domain.rbac.model.Role;
import com.astolfo.domain.rbac.repository.RoleRepository;
import com.astolfo.infrastructure.persistence.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleConverter roleConverter;


    @Override
    public List<Role> findUserRoleListById(Long id) {
        return roleConverter.toDomain(roleMapper.findUserRoleEntityListById(id));
    }

    @Override
    public List<Role> findUserRoleListByUsername(String username) {
        return roleConverter.toDomain(roleMapper.findUserRoleEntityListByUsername(username));
    }

}
