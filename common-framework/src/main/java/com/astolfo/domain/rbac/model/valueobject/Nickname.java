package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Nickname {

    private String nickname;


    public static boolean isValid(String nickname) {
        return nickname.matches("^[a-zA-Z0-9_-]{3,30}$");
    }

    public static Nickname checkOf(String nickname) {
        if (isValid(nickname)) {
            return of(nickname);
        } else {
            throw new IllegalArgumentException("昵称只能包含字母、数字、下划线、短横线，并且长度在3到30之间");
        }
    }

    public static Nickname of(String nickname) {
        if (Objects.isNull(nickname)) {
            return null;
        } else {
            return new Nickname(nickname);
        }
    }

}
