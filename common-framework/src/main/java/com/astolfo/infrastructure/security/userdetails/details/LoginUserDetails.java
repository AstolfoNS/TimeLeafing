package com.astolfo.infrastructure.security.userdetails.details;

import com.astolfo.domain.rbac.model.root.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserDetails {

     private Long userId;

    private String username;

    private String passwordHash;

    private Collection<String> authorityList;

    public static LoginUserDetails of(
            Long userId,
            String username,
            String passwordHash,
            Collection<String> authorityList
    ) {
        return new LoginUserDetails(userId, username, passwordHash, authorityList);
    }
}
