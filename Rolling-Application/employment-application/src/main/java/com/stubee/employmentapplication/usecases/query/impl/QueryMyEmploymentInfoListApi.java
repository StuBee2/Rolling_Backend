package com.stubee.employmentapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.employmentapplication.outports.QueryEmploymentByIdPort;
import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.employmentapplication.usecases.query.response.EmploymentQueryResponse;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyEmploymentInfoListApi implements QueryMyEmploymentInfoListUseCase {

    private final GetMemberInfoService getMemberInfoService;
    private final QueryEmploymentByIdPort queryEmploymentByIdPort;

    @Override
    public List<EmploymentQueryResponse> get() {
        return queryEmploymentByIdPort.findInfoByEmployeeId(getMemberInfoService.getMemberId().getId());
    }

}