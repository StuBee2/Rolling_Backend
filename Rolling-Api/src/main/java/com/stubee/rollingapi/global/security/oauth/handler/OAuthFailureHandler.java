package com.stubee.rollingapi.global.security.oauth.handler;

import com.stubee.rollingapi.global.cookie.CookieAuthorizationRequestRepository;
import com.stubee.rollingapi.global.security.exception.OAuthFailException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();

        throw OAuthFailException.EXCEPTION;
    }

}