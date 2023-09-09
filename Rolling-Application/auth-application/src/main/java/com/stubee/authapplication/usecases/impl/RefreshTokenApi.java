package com.stubee.authapplication.usecases.impl;

import com.stubee.rollingdomains.domain.auth.services.RefreshTokenService;
import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenApi implements RefreshTokenUseCase {

    private final RefreshTokenService refreshTokenService;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        return refreshTokenService.refresh(refreshToken);
    }

}