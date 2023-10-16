package com.stubee.authapplication.usecases.impl;

import com.stubee.authapplication.outports.ParseJwtPort;
import com.stubee.authapplication.outports.ProvideJwtPort;
import com.stubee.authapplication.usecases.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenApi implements RefreshTokenUseCase {

    private final ProvideJwtPort provideJwtPort;
    private final ParseJwtPort parseJwtPort;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Jws<Claims> jwsClaims = parseJwtPort.getClaimsFromRefreshToken(refreshToken);

        final Claims claims = jwsClaims.getBody();

        final String accessToken = provideJwtPort.generateAccessToken(subjectToUUID(claims), getRole(claims));

        return RefreshTokenResponse.of(accessToken);
    }

    private UUID subjectToUUID(Claims claims) {
        return UUID.fromString(claims.getSubject());
    }

    private MemberRole getRole(Claims claims) {
        return MemberRole.valueOf((String) claims.get("authority"));
    }

}