package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.memberapplication.usecases.response.MemberResponse;
import com.stubee.memberapplication.usecases.QueryMemberByIdUseCase;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMemberByIdApi implements QueryMemberByIdUseCase {

    private final QueryMemberPort queryMemberPort;

    @Override
    public MemberResponse get(final Long memberId) {
        return MemberResponse.of(queryMemberPort.getById(memberId));
    }

}