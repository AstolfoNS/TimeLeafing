package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.domain.rbac.model.Permission;
import com.astolfo.domain.domain.rbac.model.Role;
import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.PermissionRepository;
import com.astolfo.domain.domain.rbac.repository.RoleRepository;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Resource
    PermissionRepository permissionRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Long> roleIdList = user.getRoleIdList();

        List<Role> roleList = roleRepository.findRoleListByIdList(roleIdList);

        List<Long> permissionIdList = roleList
                .stream()
                .map(Role::getPermissionIdList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .distinct()
                .toList();

        List<Permission> permissionList = permissionRepository.findPermissionListByIdList(permissionIdList);

        return new LoginUserDetails(user, roleList, permissionList);
    }
}
