package com.stubee.rollingservices.domain.member.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingports.domain.member.ports.QueryMemberPort;
import com.stubee.rollingusecases.domain.member.usecases.QueryMemberByIdUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMemberByIdService implements QueryMemberByIdUseCase {

    private final QueryMemberPort queryMemberPort;

    @Override
    public Member get(final UUID memberId) {
        return queryMemberPort.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

}