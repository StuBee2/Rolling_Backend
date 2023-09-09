package com.stubee.rollingdomains.domain.auth.services.response;

public record RefreshTokenResponse(
        String accessToken) {
    public static RefreshTokenResponse of(final String accessToken) {
        return new RefreshTokenResponse(accessToken);
    }
}