package com.stubee.rollingservices.domain.member.services.query;

import com.stubee.rollingservices.common.annotations.QueryService;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.member.usecases.QueryMyInfoUseCase;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryMyInfoService implements QueryMyInfoUseCase {

    private final MemberSecurityPort securityPort;

    @Override
    public Member get() {
        return securityPort.getCurrentMember();
    }

}