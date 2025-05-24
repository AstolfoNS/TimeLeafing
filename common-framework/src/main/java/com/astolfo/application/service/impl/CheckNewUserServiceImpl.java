package com.astolfo.application.service.impl;

import com.astolfo.application.service.CheckNewUserService;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CheckNewUserServiceImpl implements CheckNewUserService {

    @Resource
    private UserRepository userRepository;


    @Override
    public boolean checkNewUser(User.NewUser newUser) {
        return !(userRepository.existsByUsername(newUser.getUsername()) || userRepository.existsByEmail(newUser.getEmail()));
    }
}
