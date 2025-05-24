package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Username {

    private String username;


    public static boolean isValid(String username) {
        return username.matches("^[a-zA-Z0-9_-]{3,30}$");
    }

    public static Username of(String username) {
        if (Objects.isNull(username)) {
            return null;
        } else {
            return new Username(username);
        }
    }

    public static Username checkOf(String username) throws IllegalArgumentException {
        if (isValid(username)) {
            return of(username);
        } else {
            throw new IllegalArgumentException("用户名只能包含字母、数字、下划线、短横线，并且长度在3到30之间");
        }
    }

}
