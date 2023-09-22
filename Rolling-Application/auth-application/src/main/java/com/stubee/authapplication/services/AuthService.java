package com.stubee.authapplication.services;

import com.stubee.authapplication.outports.CertifyAlumniPort;
import com.stubee.authapplication.outports.ParseJwtPort;
import com.stubee.authapplication.outports.ProvideJwtPort;
import com.stubee.rollingdomains.domain.auth.services.CertifyAlumniService;
import com.stubee.rollingdomains.domain.auth.services.RefreshTokenService;
import com.stubee.rollingdomains.domain.auth.services.response.RefreshTokenResponse;
import com.stubee.rollingdomains.domain.auth.consts.JwtType;
import com.stubee.rollingdomains.domain.member.consts.MemberRole;
import com.stubee.rollingdomains.domain.member.events.MemberCertifiedEvent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements RefreshTokenService, CertifyAlumniService {

    private final ProvideJwtPort provideJwtPort;
    private final ParseJwtPort parseJwtPort;
    private final CertifyAlumniPort certifyAlumniPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Jws<Claims> claims = parseJwtPort.getClaimsWithRefreshToken(refreshToken);

        parseJwtPort.isWrongType(claims, JwtType.REFRESH);

        final String accessToken = provideJwtPort.generateAccessToken(UUID.fromString(claims.getBody().getSubject()),
                (MemberRole) claims.getHeader().get("authority"));

        return RefreshTokenResponse.of(accessToken);
    }

    @Override
    public void certify(final String housemaster) {
        certifyAlumniPort.certify(housemaster);

        applicationEventPublisher.publishEvent(new MemberCertifiedEvent());
    }

}