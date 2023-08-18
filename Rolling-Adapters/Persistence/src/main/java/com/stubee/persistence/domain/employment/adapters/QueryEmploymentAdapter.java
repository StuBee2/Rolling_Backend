package com.stubee.persistence.domain.employment.adapters;

import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;
import com.stubee.persistence.common.annotations.Adapter;
import com.stubee.persistence.domain.employment.repository.QueryEmploymentRepository;
import com.stubee.rollingports.domain.employment.ports.QueryEmploymentPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@Adapter
@RequiredArgsConstructor
public class QueryEmploymentAdapter implements QueryEmploymentPort {

    private final QueryEmploymentRepository queryEmploymentRepository;

    @Override
    public List<EmploymentQueryResponse> findInfoByEmployeeId(final UUID employeeId) {
        return queryEmploymentRepository.findInfoByEmployeeId(employeeId);
    }

}