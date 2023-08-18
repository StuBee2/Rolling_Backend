package com.stubee.security.jwt.filter;

import com.stubee.authapplication.outports.ParseJwtPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final ParseJwtPort parseJwtPort;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        final String token = parseJwtPort.extractTokenFromRequest(request);

        if(token != null) {
            SecurityContextHolder.getContext().setAuthentication(parseJwtPort.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }

}