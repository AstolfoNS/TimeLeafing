package com.astolfo.v1.security.handler;

import com.astolfo.v1.common.enums.HttpCode;
import com.astolfo.v1.common.results.ResponseResult;
import com.astolfo.v1.common.utils.components.ServletUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Resource
    private ServletUtil servletUtil;


    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        servletUtil.renderJson(response, ResponseResult.errorResult(HttpCode.FORBIDDEN));
    }

}
