package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserPermissionService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserPermissionService userPermissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: loadUserByUsername

        return null;
    }
}
