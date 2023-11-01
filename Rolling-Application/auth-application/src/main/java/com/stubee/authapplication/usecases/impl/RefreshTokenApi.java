package com.stubee.authapplication.usecases.impl;

import com.stubee.authapplication.outports.ParseTokenPort;
import com.stubee.authapplication.outports.ProvideTokenPort;
import com.stubee.authapplication.usecases.RefreshTokenResponse;
import com.stubee.authapplication.usecases.RefreshTokenUseCase;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenApi implements RefreshTokenUseCase {

    private final ProvideTokenPort provideJwtPort;
    private final ParseTokenPort parseJwtPort;
    private final QueryMemberPort queryMemberPort;

    @Override
    public RefreshTokenResponse refresh(final String refreshToken) {
        final Long memberId = parseJwtPort.getSubjectFromRefreshToken(refreshToken);

        final Member member = queryMemberPort.getById(memberId);

        final String accessToken = provideJwtPort.generateAccessToken(member.memberId().getId(), member.memberDetails().memberRole());

        return RefreshTokenResponse.of(accessToken);
    }

}