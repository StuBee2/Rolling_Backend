package com.stubee.rollingapplication.domain.member.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.api.QueryMemberByIdUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.QueryMemberPort;
import com.stubee.rollingcore.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingcore.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMemberByIdService implements QueryMemberByIdUseCase {

    private final QueryMemberPort queryMemberPort;

    @Override
    public Member get(UUID memberId) {
        return queryMemberPort.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

}