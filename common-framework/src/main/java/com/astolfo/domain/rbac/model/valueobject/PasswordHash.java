package com.astolfo.domain.rbac.model.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordHash {

    private String passwordHash;

    public static boolean isValid(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$");
    }

    public static PasswordHash of(String passwordHash) {
        return new PasswordHash(passwordHash);
    }

    public static PasswordHash of(String password, PasswordEncoder passwordEncoder) throws IllegalArgumentException {
        if (isValid(password)) {
            return new PasswordHash(passwordEncoder.encode(password));
        } else {
            throw new IllegalArgumentException("密码必须包含数字和字母，且长度范围为8到30");
        }
    }

}
