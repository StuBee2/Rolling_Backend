package com.stubee.authapplication.services.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken) {}