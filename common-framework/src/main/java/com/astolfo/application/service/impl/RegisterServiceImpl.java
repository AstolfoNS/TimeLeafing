package com.astolfo.application.service.impl;

import com.astolfo.application.dto.RegisterRequest;
import com.astolfo.application.service.CheckNewUserService;
import com.astolfo.application.service.RegisterService;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.Email;
import com.astolfo.domain.rbac.model.valueobject.Nickname;
import com.astolfo.domain.rbac.model.valueobject.PasswordHash;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import com.astolfo.infrastructure.common.enumtype.HttpCode;
import com.astolfo.infrastructure.common.response.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private CheckNewUserService checkNewUserService;


    @Override
    public ResponseResult<Void> register(RegisterRequest registerRequest) {
        try {
            User.NewUser newUser = new User.NewUser();

            newUser.setEmail(Email.checkOf(registerRequest.getEmail()));

            newUser.setUsername(Username.checkOf(registerRequest.getUsername()));

            newUser.setNickname(Nickname.checkOf(registerRequest.getNickname()));

            newUser.setPasswordHash(PasswordHash.of(registerRequest.getPassword(), passwordEncoder));

            if (checkNewUserService.checkNewUser(newUser)) {
                userRepository.save(User.of(newUser));
            } else {
                throw new IllegalArgumentException("用户名或邮箱已被使用");
            }

            return ResponseResult.okResult();
        } catch (Exception exception) {
            return ResponseResult.errorResult(HttpCode.USER_REGISTER_FAILED.getCode(), exception.getMessage());
        }
    }


}
