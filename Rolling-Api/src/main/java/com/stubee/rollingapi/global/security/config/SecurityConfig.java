package com.stubee.rollingapi.global.security.config;

import com.stubee.rollingapi.global.jwt.filter.JwtExceptionFilter;
import com.stubee.rollingapi.global.jwt.filter.JwtFilter;
import com.stubee.rollingapi.global.security.oauth.handler.OAuthFailureHandler;
import com.stubee.rollingapi.global.security.oauth.handler.OAuthSuccessHandler;
import com.stubee.rollingapi.global.security.oauth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final OAuthFailureHandler oAuthFailureHandler;
    private final OAuthMemberService oAuthMemberService;
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                .requestMatchers(PATCH, "/member/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/member/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(POST, "/company").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(POST, "/company").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(DELETE, "/company/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/company/info/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/company/search/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/company/list/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/company/rank/**").permitAll()

                .requestMatchers(POST, "/employment").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/employment/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(POST, "/review").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(DELETE, "/review/**").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/review/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(POST, "/logging").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(POST, "/file").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(GET, "/news/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin().disable()

                .oauth2Login()
                .successHandler(oAuthSuccessHandler)
                .failureHandler(oAuthFailureHandler)
                .userInfoEndpoint()
                .userService(oAuthMemberService);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}