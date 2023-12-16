package com.stubee.rollingapi.domain.auth;

import jakarta.validation.constraints.NotNull;

record RefreshTokenRequest(
        @NotNull String refreshToken) {}