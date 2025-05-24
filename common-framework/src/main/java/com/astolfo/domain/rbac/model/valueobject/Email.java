package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {

    private String email;


    public static boolean isValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static Email of(String email) {
        if (Objects.isNull(email)) {
            return null;
        } else {
            return new Email(email);
        }
    }

    public static Email checkOf(String email) throws IllegalArgumentException {
        if (isValid(email)) {
            return of(email);
        }  else {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
    }

}
