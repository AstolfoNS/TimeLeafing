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
public class UserId {

    private Long userId;


    public static UserId of(Long userId) {
        if(Objects.isNull(userId)) {
            return null;
        } else {
            return new UserId(userId);
        }
    }

    public static List<Long> toLong(List<UserId> userIdList) {
        if (Objects.isNull(userIdList)) {
            return List.of();
        } else {
            return userIdList
                    .stream()
                    .map(UserId::getUserId)
                    .collect(Collectors.toList());
        }
    }

    public static List<UserId> toId(List<Long> idList) {
        if (Objects.isNull(idList)) {
            return List.of();
        } else {
            return idList
                    .stream()
                    .map(UserId::of)
                    .collect(Collectors.toList());
        }
    }

}
