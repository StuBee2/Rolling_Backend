package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.usecases.QueryMyInfoUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyInfoApi implements QueryMyInfoUseCase {

    private final GetMemberInfoService queryMemberInfoService;

    @Override
    public Member get() {
        return queryMemberInfoService.getMember();
    }

}