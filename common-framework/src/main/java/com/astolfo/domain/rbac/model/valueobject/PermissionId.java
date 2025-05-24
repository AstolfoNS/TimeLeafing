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
public class PermissionId {

    private Long permissionId;


    public static PermissionId of(Long permissionId) {
        if (Objects.isNull(permissionId)) {
            return null;
        }  else {
            return new PermissionId(permissionId);
        }
    }

    public static List<Long> toLong(List<PermissionId> permissionIdList) {
        if (Objects.isNull(permissionIdList)) {
            return List.of();
        } else {
            return permissionIdList
                    .stream()
                    .map(PermissionId::getPermissionId)
                    .collect(Collectors.toList());
        }
    }

    public static List<PermissionId> toId(List<Long> idList) {
        if (Objects.isNull(idList)) {
            return List.of();
        } else {
            return idList
                    .stream()
                    .map(PermissionId::of)
                    .collect(Collectors.toList());
        }
    }
}
