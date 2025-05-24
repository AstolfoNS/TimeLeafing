package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleId {

    private Long roleId;


    public static RoleId of(Long roleId) {
        if (Objects.isNull(roleId)) {
            return null;
        } else {
            return new RoleId(roleId);
        }
    }

    public static List<Long> toLong(List<RoleId> roleIdList) {
        if (Objects.isNull(roleIdList)) {
            return List.of();
        } else {
            return roleIdList
                    .stream()
                    .map(RoleId::getRoleId)
                    .collect(Collectors.toList());
        }
    }

    public static List<RoleId> toId(List<Long> idList) {
        if (Objects.isNull(idList)) {
            return List.of();
        } else {
        return idList
                .stream()
                .map(RoleId::of)
                .collect(Collectors.toList());
        }
    }

}
