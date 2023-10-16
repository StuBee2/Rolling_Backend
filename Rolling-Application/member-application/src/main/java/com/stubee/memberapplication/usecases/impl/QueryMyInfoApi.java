package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.memberapplication.usecases.QueryMyInfoUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyInfoApi implements QueryMyInfoUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public Member get() {
        return getCurrentMemberPort.getMember();
    }

}