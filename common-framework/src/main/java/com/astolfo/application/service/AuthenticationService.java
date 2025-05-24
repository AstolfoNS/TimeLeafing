package com.astolfo.application.service;

import com.astolfo.application.dto.LoginRequest;
import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication LoginRequestAuthenticationToken(LoginRequest loginRequest);

    Authentication getAuthenticationByLoginRequest(LoginRequest loginRequest);

    LoginUser authenticateByLoginRequest(LoginRequest loginRequest);

    User getCurrentUser();

    User getCurrentUserWithoutRoleIdList();

}
