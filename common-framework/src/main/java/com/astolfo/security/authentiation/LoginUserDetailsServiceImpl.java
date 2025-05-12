package com.astolfo.security.authentiation;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.security.userdetails.LoginUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Resource
    MenuRepository menuRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findUserByUsernameOrEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username or email is not found"));

        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        List<Menu> authorities = menuRepository.findRoleMenuListById(user.getId());

        return new LoginUserDetails(user, authorities);
    }
}
