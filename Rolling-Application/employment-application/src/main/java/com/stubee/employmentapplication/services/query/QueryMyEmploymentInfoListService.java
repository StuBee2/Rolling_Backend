package com.stubee.employmentapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.employmentapplication.outports.QueryEmploymentPort;
import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.employmentapplication.services.query.response.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyEmploymentInfoListService implements QueryMyEmploymentInfoListUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final QueryEmploymentPort queryEmploymentPort;

    @Override
    public List<EmploymentQueryResponse> get() {
        return queryEmploymentPort.findInfoByEmployeeId(memberSecurityPort.getCurrentMemberId().getId());
    }

}