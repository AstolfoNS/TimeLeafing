package com.astolfo.infrastructure.persistence.impl.service.rbac;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.domain.rbac.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findUserById(UserId userId) throws RuntimeException {
       return userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserByEmail(Email email) throws RuntimeException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserByUsername(Username username) throws RuntimeException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserWithoutRoleIdListById(UserId userId) throws RuntimeException {
        return userRepository.findUserWithoutRoleIdListById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserWithoutRoleIdListByEmail(Email email) throws RuntimeException {
        return userRepository.findUserWithoutRoleIdListByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findUserWithoutRoleIdListByUsername(Username username) throws RuntimeException {
        return userRepository.findUserWithoutRoleIdListByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
