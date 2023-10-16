package com.stubee.authapplication.usecases;

public interface RefreshTokenUseCase {

    RefreshTokenResponse refresh(String refreshToken);

}