package com.stubee.rollingapplication.domain.member.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.member.port.api.QueryMyInfoUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.member.model.Member;
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