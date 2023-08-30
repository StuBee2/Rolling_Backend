package com.stubee.employmentapplication.services.query;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.employmentapplication.outports.QueryEmploymentPort;
import com.stubee.employmentapplication.usecases.query.QueryMyEmploymentInfoListUseCase;
import com.stubee.employmentapplication.services.query.response.EmploymentQueryResponse;
import com.stubee.membershared.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryService
@RequiredArgsConstructor
public class QueryMyEmploymentInfoListService implements QueryMyEmploymentInfoListUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final QueryEmploymentPort queryEmploymentPort;

    @Override
    public List<EmploymentQueryResponse> get() {
        return queryEmploymentPort.findInfoByEmployeeId(loadCurrentMemberPort.getMemberId().getId());
    }

}