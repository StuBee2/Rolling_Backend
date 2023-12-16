package com.stubee.memberapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.memberapplication.usecases.query.MemberResponse;
import com.stubee.memberapplication.usecases.query.QueryMemberByIdUseCase;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
class QueryMemberByIdApi implements QueryMemberByIdUseCase {

    private final QueryMemberPort queryMemberPort;

    @Override
    public MemberResponse get(final Long memberId) {
        return MemberResponse.of(queryMemberPort.getById(memberId));
    }

}