package com.astolfo.security.authentiation;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.model.User;
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
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<Menu> userAuthorities = menuRepository.getMenuByUserId(user.getId());

        return new LoginUserDetails(user, userAuthorities);
    }
}
