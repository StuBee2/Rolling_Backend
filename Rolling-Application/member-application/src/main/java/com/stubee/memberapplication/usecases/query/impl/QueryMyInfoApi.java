package com.stubee.memberapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.memberapplication.usecases.query.MemberResponse;
import com.stubee.memberapplication.usecases.query.QueryMyInfoUseCase;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
class QueryMyInfoApi implements QueryMyInfoUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public MemberResponse get() {
        return MemberResponse.of(getCurrentMemberPort.getMember());
    }

}