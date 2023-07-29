package com.stubee.rollingapplication.domain.company.service;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.company.port.api.query.QueryCompanyListByMemberUseCase;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class QueryCompanyByMemberService implements QueryCompanyListByMemberUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public PageDataResponse<List<Company>> getMy(PageRequest pageRequest) {
        return getByMemberId(memberSecurityPort.getCurrentMemberId().id(), pageRequest);
    }

    @Override
    public PageDataResponse<List<Company>> getByMemberId(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findByRegistrantId(memberId, pageRequest));
    }

}