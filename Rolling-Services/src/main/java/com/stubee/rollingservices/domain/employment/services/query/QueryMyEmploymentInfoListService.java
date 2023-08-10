package com.stubee.rollingservices.domain.employment.services.query;

import com.stubee.rollingcommons.commons.annotations.QueryService;
import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;
import com.stubee.rollingports.domain.employment.ports.QueryEmploymentPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.employment.usecases.query.QueryMyEmploymentInfoListUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyEmploymentInfoListService implements QueryMyEmploymentInfoListUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryEmploymentPort queryEmploymentPort;

    @Override
    public List<EmploymentQueryResponse> get() {
        return queryEmploymentPort.findInfoByEmployeeId(memberSecurityPort.getCurrentMemberId().id());
    }

}