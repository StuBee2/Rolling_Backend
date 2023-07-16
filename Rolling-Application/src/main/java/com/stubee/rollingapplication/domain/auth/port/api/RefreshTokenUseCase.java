package com.stubee.rollingapplication.domain.auth.port.api;

import com.stubee.rollingcore.domain.auth.response.RefreshTokenResponse;

public interface RefreshTokenUseCase {

    RefreshTokenResponse refresh(String refreshToken);

}