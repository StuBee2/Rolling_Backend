package com.stubee.oauth.filter;

import com.stubee.oauth.helper.SecurityHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final SecurityHelper securityHelper;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        final String token = extractFromHeader(request);

        securityHelper.setAuthentication(token);

        chain.doFilter(request, response);
    }

    private String extractFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

}