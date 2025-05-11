package com.astolfo.security.userdetails;

import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserDetails implements UserDetails {

    private UserEntity userEntity;

    private List<MenuEntity> authorities;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities
                .stream()
                .map(menuEntity -> new SimpleGrantedAuthority(menuEntity.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    public String getStringId() {
        return userEntity.getId().toString();
    }
}
