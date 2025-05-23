package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionId {

    private Long permissionId;

    public static boolean isValid(Long permissionId) {
        return true;
    }

    public static PermissionId of(Long permissionId) {
        if (isValid(permissionId)) {
            return new PermissionId(permissionId);
        } else {
            throw new IllegalArgumentException("Invalid permissionId: " + permissionId);
        }
    }

    public static List<Long> toLong(List<PermissionId> permissionIdList) {
        return permissionIdList
                .stream()
                .map(PermissionId::getPermissionId)
                .collect(Collectors.toList());
    }

    public static List<PermissionId> toId(List<Long> idList) {
        return idList
                .stream()
                .map(PermissionId::of)
                .collect(Collectors.toList());
    }
}
