package com.stubee.rollingapplication.domain.auth.service;

import com.stubee.rollingapplication.domain.auth.port.api.RefreshTokenUseCase;
import com.stubee.rollingapplication.domain.auth.port.spi.ParseJwtPort;
import com.stubee.rollingapplication.domain.auth.port.spi.ProvideJwtPort;
import com.stubee.rollingcore.domain.auth.dto.response.RefreshTokenResponse;
import com.stubee.rollingcore.domain.member.enums.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {

    private final ProvideJwtPort provideJwtPort;
    private final ParseJwtPort parseJwtPort;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        String accessToken = reissue(parseJwtPort.getClaims(parseJwtPort.extractToken(refreshToken)));

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private String reissue(final Jws<Claims> claims) {
        return provideJwtPort.generateAccessToken(UUID.fromString(claims.getBody().getSubject()),
                (MemberRole) claims.getHeader().get("authority"));
    }

}