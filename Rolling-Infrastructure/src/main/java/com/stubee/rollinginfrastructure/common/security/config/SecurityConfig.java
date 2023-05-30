package com.stubee.rollinginfrastructure.common.security.config;

import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import com.stubee.rollinginfrastructure.common.jwt.filter.JwtFilter;
import com.stubee.rollinginfrastructure.common.security.oauth.handler.OAuthFailureHandler;
import com.stubee.rollinginfrastructure.common.security.oauth.handler.OAuthSuccessHandler;
import com.stubee.rollinginfrastructure.common.security.oauth.service.OAuthMemberService;
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

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                .requestMatchers("/member/**").hasRole("MEMBER")
                .requestMatchers("/company/**").hasRole("MEMBER")
                .requestMatchers("/review/**").hasRole("MEMBER")
                .requestMatchers("/logging/**").hasRole("MEMBER")
                .requestMatchers("/file/**").hasRole("MEMBER")
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