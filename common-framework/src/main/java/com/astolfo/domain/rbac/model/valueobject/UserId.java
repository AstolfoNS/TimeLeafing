package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserId {

    private Long userId;


    public static boolean isValid(Long userId) {
        // TODO: valid

        return true;
    }

    public static UserId of(Long userId) {
        if (isValid(userId)) {
            return new UserId(userId);
        } else {
            throw new IllegalArgumentException("Invalid userId: " + userId);
        }
    }

}
