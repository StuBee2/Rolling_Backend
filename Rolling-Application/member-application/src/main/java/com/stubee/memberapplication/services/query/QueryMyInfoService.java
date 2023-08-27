package com.stubee.memberapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.member.LoadCurrentMemberPort;
import com.stubee.memberapplication.usecases.QueryMyInfoUseCase;
import com.stubee.rollingdomains.domain.member.model.Member;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyInfoService implements QueryMyInfoUseCase {

    private final LoadCurrentMemberPort securityPort;

    @Override
    public Member get() {
        return securityPort.getCurrentMember();
    }

}