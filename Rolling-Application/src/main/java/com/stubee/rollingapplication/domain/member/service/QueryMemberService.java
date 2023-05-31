package com.stubee.rollingapplication.domain.member.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.api.QueryMemberUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingcore.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMemberService implements QueryMemberUseCase {

    private final MemberSecurityPort securityPort;
    private final QueryMemberPort queryMemberPort;

    @Override
    public Member getMy() {
        return securityPort.getCurrentMember();
    }

    @Override
    public Member getMemberById(final UUID memberId) {
        return queryMemberPort.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

}