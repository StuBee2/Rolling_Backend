package com.stubee.employmentapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.employmentapplication.outports.QueryEmploymentByIdPort;
import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.employmentapplication.usecases.query.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
class QueryMyEmploymentInfoListApi implements QueryMyEmploymentInfoListUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final QueryEmploymentByIdPort queryEmploymentByIdPort;

    @Override
    public List<EmploymentQueryResponse> get() {
        return queryEmploymentByIdPort.findInfoByEmployeeId(getCurrentMemberPort.getMemberId().getId());
    }

}