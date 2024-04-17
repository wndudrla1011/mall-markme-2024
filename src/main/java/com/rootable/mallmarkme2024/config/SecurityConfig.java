package com.rootable.mallmarkme2024.config;

import com.rootable.mallmarkme2024.handler.JwtAccessDeniedHandler;
import com.rootable.mallmarkme2024.handler.JwtAuthenticationEntryPoint;
import com.rootable.mallmarkme2024.handler.LoginFailureHandler;
import com.rootable.mallmarkme2024.handler.LoginSuccessHandler;
import com.rootable.mallmarkme2024.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        log.info("---------------------security config---------------------------");

        //CORS 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        //세션 Disable
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(AbstractHttpConfigurer::disable);

        //로그인 결과 핸들링
        http.formLogin(config -> {
            config.loginPage("/api/member/login");
            config.successHandler(new LoginSuccessHandler());
            config.failureHandler(new LoginFailureHandler());
        });

        //필터 등록
        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        //예외 핸들링
        http.exceptionHandling(config -> {
            config.accessDeniedHandler(new JwtAccessDeniedHandler());
            config.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        });

        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }

}
