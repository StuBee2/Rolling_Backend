package com.stubee.authapplication.usecases.impl;

import com.stubee.authapplication.outports.ParseJwtPort;
import com.stubee.authapplication.outports.ProvideJwtPort;
import com.stubee.authapplication.usecases.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.services.GetMemberByIdService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenApi implements RefreshTokenUseCase {

    private final ProvideJwtPort provideJwtPort;
    private final ParseJwtPort parseJwtPort;
    private final GetMemberByIdService getMemberByIdService;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Jws<Claims> jwsClaims = parseJwtPort.getClaimsFromRefreshToken(refreshToken);

        final Claims claims = jwsClaims.getBody();

        final Member member = getMemberByIdService.getById(Long.parseLong(claims.getSubject()));

        final String accessToken = provideJwtPort.generateAccessToken(member.memberId().getId(), member.memberDetails().memberRole());

        return RefreshTokenResponse.of(accessToken);
    }

}