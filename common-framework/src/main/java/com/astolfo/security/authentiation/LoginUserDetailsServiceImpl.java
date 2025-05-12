package com.astolfo.security.authentiation;

import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.security.userdetails.LoginUserDetails;
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
    MenuRepository menuRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userEntity == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return new LoginUserDetails(userEntity, authorities);
    }
}
