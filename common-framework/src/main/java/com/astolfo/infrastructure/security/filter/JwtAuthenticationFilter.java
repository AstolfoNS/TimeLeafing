package com.astolfo.infrastructure.security.filter;

import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.util.component.JwtUtil;
import com.astolfo.infrastructure.common.util.component.RedisCacheUtil;
import com.astolfo.infrastructure.security.userdetails.LoginUser;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisCacheUtil redisCacheUtil;


    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("authorization");

        if (!(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))) {
            filterChain.doFilter(request, response);

            return;
        }

        String token = authHeader.substring("Bearer ".length());

        String userId = jwtUtil.parseToken(token).getStringId();

        LoginUser loginUser = redisCacheUtil.get(RedisCacheConstant.Login_USER_PERFIX.concat(userId));

        if (Objects.isNull(loginUser)) {
            log.error("JWT认证过程中未从redis中找到用户，用户未登录，UserId: {}", userId);

            throw new RuntimeException("用户未登录");
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities()));

        filterChain.doFilter(request, response);
    }
}
