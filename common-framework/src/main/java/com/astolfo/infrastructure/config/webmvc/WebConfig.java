package com.astolfo.infrastructure.config.webmvc;

import org.jetbrains.annotations.NotNull;
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

    private static class ApiPrefixHandlerMapping extends RequestMappingHandlerMapping {
        private final String prefix;

        public ApiPrefixHandlerMapping(String prefix) {
            this.prefix = prefix;
        }

        @Override
        protected void registerHandlerMethod(
                @NotNull Object handler,
                Method method,
                @NotNull RequestMappingInfo mapping
        ) {
            RequestMappingInfo newMapping = RequestMappingInfo
                    .paths(this.prefix)
                    .build()
                    .combine(mapping);

            super.registerHandlerMethod(handler, method, newMapping);
        }
    }

}
