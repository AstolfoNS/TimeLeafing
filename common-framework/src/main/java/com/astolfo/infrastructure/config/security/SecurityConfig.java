package com.astolfo.infrastructure.config.security;

import com.astolfo.infrastructure.security.filter.JwtAuthenticationFilter;
import com.astolfo.infrastructure.security.handler.AccessDeniedHandlerImpl;
import com.astolfo.infrastructure.security.handler.AuthenticationEntryPointImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(
                        exceptionHandling -> {
                                exceptionHandling.accessDeniedHandler(accessDeniedHandler);
                                exceptionHandling.authenticationEntryPoint(authenticationEntryPoint);
                        }
                )
                .sessionManagement(
                        session -> {
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        }
                )
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/guest-auth/login", "/guest-auth/register").permitAll();
                            auth.requestMatchers("/admin-auth/login", "/admin-auth/register").permitAll();
                            auth.anyRequest().authenticated();
                        }
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
