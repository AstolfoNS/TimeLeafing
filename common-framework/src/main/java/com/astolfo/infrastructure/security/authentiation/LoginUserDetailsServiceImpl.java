package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.domain.service.UserPermissionService;
import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserPermissionService userPermissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Permission> permissionList = userPermissionService.findPermissionListByUser(user);

        return new LoginUserDetails(user, permissionList);
    }
}
