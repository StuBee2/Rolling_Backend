package com.stubee.authapplication.usecases;

import com.stubee.authapplication.services.response.RefreshTokenResponse;

public interface RefreshTokenUseCase {

    RefreshTokenResponse refresh(String refreshToken);

}