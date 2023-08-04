package com.stubee.rollingapplication.domain.employment.service.query;

import com.stubee.rollingapplication.common.annotation.QueryService;
import com.stubee.rollingapplication.domain.employment.port.api.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.rollingapplication.domain.employment.port.spi.QueryEmploymentPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.employment.response.EmploymentQueryResponse;
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