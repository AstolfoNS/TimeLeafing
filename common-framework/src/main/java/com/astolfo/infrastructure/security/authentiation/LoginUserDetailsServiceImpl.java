package com.astolfo.infrastructure.security.authentiation;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.service.UserPermissionService;
import com.astolfo.domain.rbac.service.UserService;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Resource
    UserPermissionService userPermissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        return getLoginUserDetails(userService.findUserByUsername(Username.checkOf(username)));
    }

    private LoginUser getLoginUserDetails(User user) {
        LoginUser.Details details = new LoginUser.Details();

        details.setUserId(Objects.isNull(user.getId()) ? null : user.getId().getUserId());

        details.setUsername(Objects.isNull(user.getUsername()) ? null : user.getUsername().getUsername());

        details.setPassword(Objects.isNull(user.getPasswordHash()) ? null : user.getPasswordHash().getPasswordHash());

        details.setAuthorityList(userPermissionService.findPermissionSymbolNameByUser(user));

        return LoginUser.of(details);
    }
}
