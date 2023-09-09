package com.stubee.authapplication.usecases;

import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;

public interface RefreshTokenUseCase {

    RefreshTokenResponse refresh(String refreshToken);

}