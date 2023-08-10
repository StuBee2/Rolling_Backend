package com.stubee.rollingexternal.global.security.oauth.handler;

import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingexternal.global.cookie.CookieManagerImpl;
import com.stubee.rollingexternal.global.security.model.CustomMemberDetails;
import com.stubee.rollingexternal.global.cookie.CookieAuthorizationRequestRepository;
import com.stubee.rollingports.domain.auth.ports.ProvideJwtPort;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ProvideJwtPort provideJwtPort;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final CookieManagerImpl cookieManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        clearAuthenticationAttributesAndCookies(request);

        super.getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        this.isNotMatchedUri(cookieManager.getCookie(CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue));

        //String targetUrl = redirectUri.orElse("/login/success");

        Member member = ((CustomMemberDetails) authentication.getPrincipal()).getMember();

        final String accessToken = provideJwtPort.generateAccessToken(member.memberId().id(), member.memberDetails().memberRole());
        final String refreshToken = provideJwtPort.generateRefreshToken(member.memberId().id(), member.memberDetails().memberRole());

        return UriComponentsBuilder.fromUriString("http://localhost:3000/callback")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
    }

    private void isNotMatchedUri(final Optional<String> redirectUri) {
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("redirect URIs are not matched");
        }
    }

    private void clearAuthenticationAttributesAndCookies(HttpServletRequest request) {
        super.clearAuthenticationAttributes(request);

        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        URI clientRedirectUri = URI.create(uri);
        //URI authorizedUri = URI.create(redirectUri);
        URI authorizedUri = URI.create("/login/success");

        return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort();
    }

}