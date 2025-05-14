package com.astolfo.infrastructure.security.userdetails;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUserDetails implements UserDetails {

    private User user;

    private List<Permission> permissionList;


    public LoginUserDetails(User user) {
        setUser(user);

        permissionList =
    }

    public Long getId() {
        return user.getId();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getSymbol().getSymbol()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
