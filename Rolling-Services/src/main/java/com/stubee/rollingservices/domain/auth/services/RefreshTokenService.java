package com.stubee.rollingservices.domain.auth.services;

import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.auth.response.RefreshTokenResponse;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.rollingports.domain.auth.ports.ParseJwtPort;
import com.stubee.rollingports.domain.auth.ports.ProvideJwtPort;
import com.stubee.rollingusecases.domain.auth.usecases.RefreshTokenUseCase;
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
        final Jws<Claims> claims = parseJwtPort.getClaims(parseJwtPort.extractToken(refreshToken));

        parseJwtPort.isWrongType(claims, JwtType.REFRESH);

        return reissueAccessToken(claims);
    }

    private RefreshTokenResponse reissueAccessToken(final Jws<Claims> claims) {
        final String accessToken = provideJwtPort.generateAccessToken(UUID.fromString(claims.getBody().getSubject()),
                (MemberRole) claims.getHeader().get("authority"));

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

}