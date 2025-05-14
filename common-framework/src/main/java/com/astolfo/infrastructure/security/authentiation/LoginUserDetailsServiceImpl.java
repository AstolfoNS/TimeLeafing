package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
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
    PermissionRepository menuRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new LoginUserDetails(userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")), menuRepository.findUserMenuListByUsername(username));
    }
}
