package com.astolfo.infrastructure.config.webmvc;

import jakarta.annotation.Nonnull;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiPrefixHandlerMapping("/api");
    }

    @Setter
    private static class ApiPrefixHandlerMapping extends RequestMappingHandlerMapping {
        private String prefix;

        public ApiPrefixHandlerMapping(String prefix) {
            setPrefix(prefix);
        }

        private RequestMappingInfo getNewRequestMapping(RequestMappingInfo mapping) {
            return RequestMappingInfo
                    .paths(this.prefix)
                    .build()
                    .combine(mapping);
        }

        @Override
        protected void registerHandlerMethod(
                @Nonnull Object handler,
                @Nonnull Method method,
                @Nonnull RequestMappingInfo mapping
        ) {
            super.registerHandlerMethod(handler, method, getNewRequestMapping(mapping));
        }
    }

}
