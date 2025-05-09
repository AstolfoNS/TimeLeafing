package com.astolfo.v1.common.utils.components;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServletUtil {

    @Resource
    private ObjectMapper objectMapper;


    public boolean renderString(HttpServletResponse response, String string) {
        return renderString(response, HttpServletResponse.SC_OK, string);
    }

    public boolean renderString(
            HttpServletResponse response,
            int status,
            String string
    ) {
        try {
            response.setStatus(status);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().print(string);

            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    public boolean renderJson(HttpServletResponse response, Object data) {
        try {
            return renderString(response, objectMapper.writeValueAsString(data));
        } catch (Exception exception) {
            return false;
        }
    }

}
