package com.astolfo.security.service.impl;

import com.astolfo.mapper.UserMapper;
import com.astolfo.model.entity.User;
import com.astolfo.security.entity.LoginUser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Username or password is incorrect");
        }

        return new LoginUser(user);
    }
}
