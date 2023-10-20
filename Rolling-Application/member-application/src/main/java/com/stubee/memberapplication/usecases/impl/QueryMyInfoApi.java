package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.memberapplication.usecases.response.MemberResponse;
import com.stubee.memberapplication.usecases.QueryMyInfoUseCase;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyInfoApi implements QueryMyInfoUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public MemberResponse get() {
        return MemberResponse.of(getCurrentMemberPort.getMember());
    }

}