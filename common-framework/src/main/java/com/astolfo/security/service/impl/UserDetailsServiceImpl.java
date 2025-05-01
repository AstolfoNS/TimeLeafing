package com.astolfo.security.service.impl;

import com.astolfo.mapper.UserMapper;
import com.astolfo.model.vo.UserVO;
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
        UserVO userVO = userMapper.getUserVOByUsername(username);

        if (Objects.isNull(userVO)) {
            throw new UsernameNotFoundException("<UNK>");
        }
    }
}
