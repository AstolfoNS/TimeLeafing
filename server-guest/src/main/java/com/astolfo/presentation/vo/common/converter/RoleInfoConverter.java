package com.astolfo.presentation.vo.common.converter;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.presentation.vo.RoleInfo;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleInfoConverter {

    public RoleInfo toVo(Role role) {
        if (role == null) {
            return null;
        } else {
            return new RoleInfo(role.getName(), role.getDescription());
        }
    }

    public List<RoleInfo> toVo(List<Role> roleList) {
        if (roleList == null) {
            return null;
        } else {
            return roleList
                    .stream()
                    .map(this::toVo)
                    .collect(Collectors.toList());
        }
    }

}
