package com.astolfo.domain.rbac.repository;

import com.astolfo.domain.rbac.model.Menu;

import java.util.List;

public interface PermissionRepository {

    List<Menu> findMenuListByUserUsername(String Username);

}
