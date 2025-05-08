package com.astolfo.security.handler;

import com.astolfo.common.enums.HttpCode;
import com.astolfo.common.results.ResponseResult;
import com.astolfo.common.utils.components.ServletUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Resource
    private ServletUtil servletUtil;


    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        servletUtil.renderJson(response, ResponseResult.errorResult(HttpCode.UNAUTHORIZED));
    }

}
