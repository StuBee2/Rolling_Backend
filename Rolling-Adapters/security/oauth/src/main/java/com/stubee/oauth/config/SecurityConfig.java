package com.stubee.oauth.config;

import com.stubee.jwt.filter.ExceptionFilter;
import com.stubee.jwt.filter.JwtExceptionFilter;
import com.stubee.oauth.filter.TokenFilter;
import com.stubee.oauth.handler.CustomAccessDeniedHandler;
import com.stubee.oauth.handler.OAuthFailureHandler;
import com.stubee.oauth.handler.OAuthSuccessHandler;
import com.stubee.oauth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
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
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final OAuthMemberService oAuthMemberService;
    private final ExceptionFilter exceptionFilter;
    private final TokenFilter tokenFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(exceptionFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, TokenFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                .requestMatchers("/auth/refresh").permitAll()
                .requestMatchers("/auth/certify").hasRole("TEMP")

                //Member
                .requestMatchers(PATCH, "/member/**").authenticated()

                .requestMatchers(GET, "/member/my").authenticated()
                .requestMatchers(GET, "/member/**").authenticated()

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

                //Story
                .requestMatchers(POST, "/story").hasAnyRole("MEMBER", "ADMIN")
                .requestMatchers(DELETE, "/story/**").hasAnyRole("MEMBER", "ADMIN")

                .requestMatchers(GET, "/story/my").authenticated()
                .requestMatchers(GET, "/story/info/**").permitAll()
                .requestMatchers(GET, "/story/list/**").permitAll()

                //Logging
                .requestMatchers(POST, "/logging").authenticated()

                //File
                .requestMatchers(POST, "/file").hasAnyRole("MEMBER", "ADMIN")

                //News
                .requestMatchers(GET, "/news/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin().disable()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

                .and()
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