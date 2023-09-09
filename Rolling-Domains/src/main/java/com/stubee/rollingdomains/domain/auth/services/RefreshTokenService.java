package com.stubee.rollingdomains.domain.auth.services;

import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;

public interface RefreshTokenService {

    RefreshTokenResponse refresh(final String refreshToken);

}