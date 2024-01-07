package rolling.rollingapi.auth;

import jakarta.validation.constraints.NotBlank;

record RefreshTokenRequest(@NotBlank String refreshToken) {}