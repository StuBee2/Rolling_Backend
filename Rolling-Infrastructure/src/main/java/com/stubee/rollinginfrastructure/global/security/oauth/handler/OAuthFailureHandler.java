package com.stubee.rollinginfrastructure.global.security.oauth.handler;

import com.stubee.rollinginfrastructure.global.cookie.CookieAuthorizationRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();

        final String url = UriComponentsBuilder.fromUriString("http://localhost:3000/callback")
                .queryParam("error", exception.getLocalizedMessage()).build().toUriString();

        super.getRedirectStrategy()
                .sendRedirect(request, response, url);
    }

}