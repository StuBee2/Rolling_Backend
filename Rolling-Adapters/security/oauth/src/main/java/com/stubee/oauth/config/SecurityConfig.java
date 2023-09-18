package com.stubee.oauth.config;

import com.stubee.oauth.filter.ExceptionFilter;
import com.stubee.oauth.filter.JwtExceptionFilter;
import com.stubee.oauth.filter.JwtFilter;
import com.stubee.oauth.handler.OAuthFailureHandler;
import com.stubee.oauth.handler.OAuthSuccessHandler;
import com.stubee.oauth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
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
    private final ExceptionFilter exceptionFilter;
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(exceptionFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                //Member
                .requestMatchers(PATCH, "/member/**").authenticated()

                .requestMatchers(GET, "/member/my").authenticated()
                .requestMatchers(GET, "/member/**").permitAll()

                //Company
                .requestMatchers(POST, "/company").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(PATCH, "/company/status").hasAnyRole("ADMIN")
                .requestMatchers(DELETE, "/company/**").hasAnyRole("ADMIN")

                .requestMatchers(GET, "/company/info/**").permitAll()
                .requestMatchers(GET, "/company/search/**").permitAll()
                .requestMatchers(GET, "/company/list/**").permitAll()
                .requestMatchers(GET, "/company/rank/**").permitAll()

                //Employment
                .requestMatchers(POST, "/employment").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(GET, "/employment/my").hasAnyRole("MEMBER", "ADMIN")

                //Review
                .requestMatchers(POST, "/review").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(DELETE, "/review/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(GET, "/review/my").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(GET, "/review/info/**").permitAll()
                .requestMatchers(GET, "/review/list/**").permitAll()

                //Logging
                .requestMatchers(POST, "/logging").authenticated()

                //File
                .requestMatchers(POST, "/file").hasAnyRole("MEMBER", "ADMIN")

                //News
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