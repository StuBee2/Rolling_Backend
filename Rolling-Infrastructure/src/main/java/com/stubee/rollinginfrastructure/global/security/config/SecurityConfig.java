package com.stubee.rollinginfrastructure.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import com.stubee.rollinginfrastructure.global.jwt.filter.JwtExceptionFilter;
import com.stubee.rollinginfrastructure.global.jwt.filter.JwtFilter;
import com.stubee.rollinginfrastructure.global.security.oauth.handler.OAuthFailureHandler;
import com.stubee.rollinginfrastructure.global.security.oauth.handler.OAuthSuccessHandler;
import com.stubee.rollinginfrastructure.global.security.oauth.service.OAuthMemberService;
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
    private final ParseJwtPort parseJwtPort;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterAfter(new JwtFilter(parseJwtPort), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(new ObjectMapper()), JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                .requestMatchers(PATCH, "/member/**").hasRole(MEMBER())
                .requestMatchers(GET, "/member/**").hasRole(MEMBER())

                .requestMatchers(POST, "/company").hasRole(MEMBER())
                .requestMatchers(DELETE, "/company/**").hasRole(MEMBER())
                .requestMatchers(GET, "/company/info/**").hasRole(MEMBER())
                .requestMatchers(GET, "/company/search/**").hasRole(MEMBER())
                .requestMatchers(GET, "/company/list/**").hasRole(MEMBER())
                .requestMatchers(GET, "/company/rank/**").permitAll()

                .requestMatchers(POST, "/employment").hasRole(MEMBER())

                .requestMatchers(POST, "/review").hasRole(MEMBER())
                .requestMatchers(DELETE, "/review/**").hasRole(MEMBER())
                .requestMatchers(GET, "/review/**").hasRole(MEMBER())

                .requestMatchers(POST, "/logging").hasRole(MEMBER())

                .requestMatchers(POST, "/file").hasRole(MEMBER())

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

    public static String MEMBER() {
        return MemberRole.MEMBER.toString();
    }

}