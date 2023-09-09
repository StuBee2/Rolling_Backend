package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.usecases.QueryMemberByIdUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.services.GetMemberByIdService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryMemberByIdApi implements QueryMemberByIdUseCase {

    private final GetMemberByIdService queryMemberByIdService;

    @Override
    public Member get(final UUID memberId) {
        return queryMemberByIdService.getById(memberId);
    }

}