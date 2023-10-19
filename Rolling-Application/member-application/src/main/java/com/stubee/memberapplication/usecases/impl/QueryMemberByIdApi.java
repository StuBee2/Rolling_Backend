package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.usecases.response.MemberResponse;
import com.stubee.memberapplication.usecases.QueryMemberByIdUseCase;
import com.stubee.rollingdomains.domain.member.services.GetMemberByIdService;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMemberByIdApi implements QueryMemberByIdUseCase {

    private final GetMemberByIdService queryMemberByIdService;

    @Override
    public MemberResponse get(final Long memberId) {
        return MemberResponse.of(queryMemberByIdService.getById(memberId));
    }

}