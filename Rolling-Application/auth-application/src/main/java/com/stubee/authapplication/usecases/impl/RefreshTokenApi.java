package com.stubee.authapplication.usecases.impl;

import com.stubee.authapplication.outports.ParseTokenPort;
import com.stubee.authapplication.outports.ProvideTokenPort;
import com.stubee.authapplication.usecases.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.services.GetMemberByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenApi implements RefreshTokenUseCase {

    private final ProvideTokenPort provideJwtPort;
    private final ParseTokenPort parseJwtPort;
    private final GetMemberByIdService getMemberByIdService;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Long memberId = parseJwtPort.getSubjectFromRefreshToken(refreshToken);

        final Member member = getMemberByIdService.getById(memberId);

        final String accessToken = provideJwtPort.generateAccessToken(member.memberId().getId(), member.memberDetails().memberRole());

        return RefreshTokenResponse.of(accessToken);
    }

}