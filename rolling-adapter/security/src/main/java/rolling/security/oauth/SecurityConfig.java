package rolling.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import rolling.security.jwt.ExceptionFilter;
import rolling.security.jwt.JwtExceptionFilter;
import rolling.security.jwt.JwtFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final AccessDeniedHandler accessDeniedHandler;
    private final DefaultOAuth2UserService oAuthMemberService;
    private final ExceptionFilter exceptionFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(exceptionFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)

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
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

                .and()
                .oauth2Login()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
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