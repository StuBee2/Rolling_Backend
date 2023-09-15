package com.stubee.authapplication.services;

import com.stubee.authapplication.outports.ParseJwtPort;
import com.stubee.authapplication.outports.ProvideJwtPort;
import com.stubee.rollingdomains.domain.auth.services.RefreshTokenService;
import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements RefreshTokenService {

    private final ProvideJwtPort provideJwtPort;
    private final ParseJwtPort parseJwtPort;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Jws<Claims> claims = parseJwtPort.getClaimsWithRefreshToken(refreshToken);

        parseJwtPort.isWrongType(claims, JwtType.REFRESH);

        final String accessToken = provideJwtPort.generateAccessToken(UUID.fromString(claims.getBody().getSubject()),
                (MemberRole) claims.getHeader().get("authority"));

        return RefreshTokenResponse.of(accessToken);
    }

}