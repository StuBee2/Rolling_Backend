package com.stubee.memberapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.memberapplication.usecases.QueryMemberByIdUseCase;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
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