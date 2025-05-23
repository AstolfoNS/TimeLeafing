package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserPermissionService;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
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
        return getLoginUserDetails(userRepository.findUserByUsername(Username.of(username)).orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    private LoginUser getLoginUserDetails(User user) {
        LoginUser.Details details = new LoginUser.Details();

        details.setUserId(user.getId().getUserId());

        details.setUsername(user.getUsername().getUsername());

        details.setPassword(user.getPasswordHash().getPasswordHash());

        details.setAuthorityList(userPermissionService.findPermissionSymbolNameByUser(user));

        return LoginUser.of(details);
    }
}
