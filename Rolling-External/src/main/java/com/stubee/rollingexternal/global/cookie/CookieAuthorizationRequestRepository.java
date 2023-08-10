package com.stubee.rollingexternal.global.cookie;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int COOKIE_EXPIRE_SECONDS = 180;
    private final CookieManagerImpl cookieManager;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return cookieManager.getDeserializedCookie(OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, OAuth2AuthorizationRequest.class)
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest==null) {
            cookieManager.deleteCookie(OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            cookieManager.deleteCookie(REDIRECT_URI_PARAM_COOKIE_NAME);

            return;
        }

        cookieManager.addSerializedCookie(OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, authorizationRequest, COOKIE_EXPIRE_SECONDS);

        addRedirectUriAfterLogin(request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME));
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies() {
        cookieManager.deleteCookie(OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        cookieManager.deleteCookie(REDIRECT_URI_PARAM_COOKIE_NAME);
    }

    private void addRedirectUriAfterLogin(final String redirectUriAfterLogin) {
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            cookieManager.addSerializedCookie(REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, COOKIE_EXPIRE_SECONDS);
        }
    }

}