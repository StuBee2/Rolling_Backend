package com.stubee.rollingservices.domain.company.services;

import com.stubee.rollingservices.common.annotations.QueryService;
import com.stubee.rollingdomains.common.dto.request.PageRequest;
import com.stubee.rollingdomains.common.dto.response.PageDataResponse;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.company.usecases.query.QueryCompanyListByMemberUseCase;
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
        return getByMemberId(memberSecurityPort.getCurrentMemberId().getId(), pageRequest);
    }

    @Override
    public PageDataResponse<List<Company>> getByMemberId(final UUID memberId, PageRequest pageRequest) {
        return PageDataResponse.create(queryCompanyPort.findByRegistrantId(memberId, pageRequest));
    }

}