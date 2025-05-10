package com.astolfo.security.filter;

import com.astolfo.infrastructure.common.constant.RedisCacheConstant;
import com.astolfo.infrastructure.common.util.JwtUtil;
import com.astolfo.infrastructure.common.util.RedisCacheUtil;
import com.astolfo.security.userdetails.LoginUserDetails;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

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
        String authHeader = request.getHeader("token");

        if (!(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))) {
            filterChain.doFilter(request, response);

            return;
        }

        String token = authHeader.substring("Bearer ".length());

        String userId = jwtUtil.parseToken(token).getStringId();

        LoginUserDetails loginUserDetails = redisCacheUtil.getObject(RedisCacheConstant.Login_USER_PERFIX.concat(userId));

        if (Objects.isNull(loginUserDetails)) {
            throw new RuntimeException("User is not logged in");
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities()));

        filterChain.doFilter(request, response);
    }
}
