package com.astolfo.infrastructure.security.util;


import com.astolfo.infrastructure.security.userdetails.LoginUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

@Slf4j
public final class SecurityUtil {

    private SecurityUtil() {
        throw new IllegalStateException("非实例化工具类");
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UserDetails getUserDetails() {
        Authentication authentication = getAuthentication();

        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public static LoginUser getRequiredLoginUser() {
        UserDetails userDetails = getUserDetails();

        if (Objects.isNull(userDetails) || !(userDetails instanceof LoginUser)) {
            log.error("userDetails不合法");

            throw new IllegalStateException("userDetails不合法");
        } else {
            return (LoginUser) userDetails;
        }
    }

    public static Long getRequiredCurrentUserId() {
        return getRequiredLoginUser().getUserId();
    }

    public static String getRequiredCurrentUserName() {
        return getRequiredLoginUser().getUsername();
    }

}