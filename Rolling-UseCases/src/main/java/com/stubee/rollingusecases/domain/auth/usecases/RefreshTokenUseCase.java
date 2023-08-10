package com.stubee.rollingusecases.domain.auth.usecases;

import com.stubee.rollingdomains.domain.auth.response.RefreshTokenResponse;

public interface RefreshTokenUseCase {

    RefreshTokenResponse refresh(String refreshToken);

}