package com.astolfo.presentation.vo.converter;

import com.astolfo.domain.rbac.model.Role;
import com.astolfo.presentation.vo.RoleInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用于将domain层中的model -> vo
 *
 *
 */
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
                    .map(role -> Optional.of(toVo(role)).orElseThrow(() -> new NoSuchElementException("Can not convert null role")))
                    .collect(Collectors.toList());
        }
    }

}
