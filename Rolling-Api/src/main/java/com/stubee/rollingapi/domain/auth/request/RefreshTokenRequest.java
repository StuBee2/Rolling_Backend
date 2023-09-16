package com.stubee.rollingapi.domain.auth.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull String refreshToken) {}