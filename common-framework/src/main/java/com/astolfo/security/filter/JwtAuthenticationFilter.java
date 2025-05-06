package com.astolfo.security.filter;

import com.astolfo.common.utils.JwtUtil;
import com.astolfo.security.entity.LoginUser;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("token");

        if (!(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))) {
            filterChain.doFilter(request, response);

            return;
        }

        String token = authHeader.substring("Bearer ".length());

        String username = jwtUtil.parseToken(token).getUsername();
        List<String> roles = jwtUtil.parseToken(token).getAuthorities();
    }
}
