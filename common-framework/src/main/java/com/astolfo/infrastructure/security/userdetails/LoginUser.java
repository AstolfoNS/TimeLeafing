package com.astolfo.infrastructure.security.userdetails;

import com.astolfo.infrastructure.security.userdetails.details.LoginUserDetails;
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

    private LoginUserDetails loginUserDetails;


    public String getStringId() {
        return loginUserDetails.getUserId().toString();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return loginUserDetails
                .getAuthorityList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return loginUserDetails.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return loginUserDetails.getUsername();
    }

}
