package com.stubee.rollingcore.domain.auth.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken) {}