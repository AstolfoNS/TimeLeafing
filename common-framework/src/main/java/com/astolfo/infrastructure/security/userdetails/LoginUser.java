package com.astolfo.infrastructure.security.userdetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUser implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Collection<String> authorityList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Details {

        private Long userId;

        private String username;

        private String password;

        private Collection<String> authorityList;
    }

    private LoginUser(Details loginUserDetails) {
        setUserId(loginUserDetails.getUserId());

        setUsername(loginUserDetails.getUsername());

        setPassword(loginUserDetails.getPassword());

        setAuthorityList(loginUserDetails.getAuthorityList());
    }

    public static LoginUser of(Details loginUserDetails) {
        return new LoginUser(loginUserDetails);
    }

    @JsonIgnore
    public String getStringId() {
        return userId.toString();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
