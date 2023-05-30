package com.stubee.rollingcore.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken) {}