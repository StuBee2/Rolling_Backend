package com.stubee.rollinginfrastructure.global.security.oauth.handler;

import com.stubee.rollingapplication.domain.auth.port.spi.ProvideJwtPort;
import com.stubee.rollingcore.domain.member.model.Member;
import com.stubee.rollinginfrastructure.global.security.model.CustomMemberDetails;
import com.stubee.rollinginfrastructure.global.cookie.CookieAuthorizationRequestRepository;
import com.stubee.rollinginfrastructure.global.cookie.CookieManager;
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
    private final CookieManager cookieManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, authentication);

        if (response.isCommitted()) {
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
        Optional<String> redirectUri = cookieManager.getCookie(CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("redirect URIs are not matched");
        }

        //String targetUrl = redirectUri.orElse("/login/success");

        Member member = ((CustomMemberDetails) authentication.getPrincipal()).getMember();

        return UriComponentsBuilder.fromUriString("http://localhost:3000/callback")
                .queryParam("accessToken", provideJwtPort.generateAccessToken(member.memberId().id(), member.memberDetails().memberRole()))
                .queryParam("refreshToken", provideJwtPort.generateRefreshToken(member.memberId().id(), member.memberDetails().memberRole()))
                .build().toUriString();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        //URI authorizedUri = URI.create(redirectUri);
        URI authorizedUri = URI.create("/login/success");

        return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort();
    }

}