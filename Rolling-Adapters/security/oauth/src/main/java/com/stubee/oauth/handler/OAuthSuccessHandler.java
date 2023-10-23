package com.stubee.oauth.handler;

import com.stubee.authapplication.outports.ProvideTokenPort;
import com.stubee.oauth.cookie.CookieAuthorizationRequestRepository;
import com.stubee.oauth.model.CustomMemberDetails;
import com.stubee.oauth.properties.OAuthProperties;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.securitycommons.cookie.CookieManager;
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

import static com.stubee.oauth.cookie.CookieAuthorizationRequestRepository.*;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ProvideTokenPort provideJwtPort;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final CookieManager cookieManager;
    private final OAuthProperties oAuthProperties;

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
        isNotMatchedUri(cookieManager.getCookie(REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue));

        final Member member = ((CustomMemberDetails) authentication.getPrincipal()).getMember();

        final Long memberId = member.memberId().getId();
        final MemberRole memberRole = member.memberDetails().memberRole();

        final String redirectUrl = oAuthProperties.getRedirectUrl();

        final String accessToken = provideJwtPort.generateAccessToken(memberId, memberRole);
        final String refreshToken = provideJwtPort.generateRefreshToken(memberId, memberRole);

        return UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
    }

    private void clearAuthenticationAttributesAndCookies(HttpServletRequest request) {
        super.clearAuthenticationAttributes(request);

        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();
    }

    private void isNotMatchedUri(final Optional<String> redirectUri) {
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("redirect URIs are not matched");
        }
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create("/login/success");

        return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort();
    }

}