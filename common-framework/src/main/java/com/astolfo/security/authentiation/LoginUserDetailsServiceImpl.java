package com.astolfo.security.authentiation;

import com.astolfo.domain.rbac.model.Menu;
import com.astolfo.domain.rbac.repository.MenuRepository;
import com.astolfo.domain.rbac.model.User;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.persistence.entity.MenuEntity;
import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.astolfo.infrastructure.persistence.mapper.MenuMapper;
import com.astolfo.infrastructure.persistence.mapper.UserMapper;
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
    UserMapper userMapper;

    @Resource
    MenuMapper menuMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userMapper.findUserEntityByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        List<MenuEntity> authorities = menuMapper.findMenuEntityListByUserUsername(username);

        return new LoginUserDetails(userEntity, authorities);
    }
}
