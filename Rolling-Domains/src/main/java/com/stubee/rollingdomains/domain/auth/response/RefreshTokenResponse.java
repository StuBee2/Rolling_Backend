package com.stubee.rollingdomains.domain.auth.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken) {}