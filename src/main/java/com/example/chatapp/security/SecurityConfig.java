package com.example.chatapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 비활성화 (H2 콘솔 사용 시 필요)
                .headers().frameOptions().disable() // H2 콘솔을 위한 X-Frame-Options 비활성화
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 URL 허용
                .anyRequest().authenticated(); // 나머지 요청은 인증 필요
        return http.build();
    }
}
