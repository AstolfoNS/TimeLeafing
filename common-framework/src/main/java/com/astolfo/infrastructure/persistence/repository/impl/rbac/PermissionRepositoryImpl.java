package com.astolfo.infrastructure.persistence.repository.impl.rbac;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.repository.PermissionRepository;

import java.util.List;

public class PermissionRepositoryImpl implements PermissionRepository {

    @Override
    public List<Menu> findMenuListByUserUsername(String Username) {
        return List.of();
    }

}
