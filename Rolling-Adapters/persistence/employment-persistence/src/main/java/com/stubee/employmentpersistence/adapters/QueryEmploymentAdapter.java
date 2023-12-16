package com.stubee.employmentpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.employmentapplication.outports.QueryEmploymentByIdPort;
import com.stubee.employmentapplication.usecases.query.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
class QueryEmploymentAdapter implements QueryEmploymentByIdPort, CheckEmploymentExistencePort {

    private final QueryEmploymentRepository queryEmploymentRepository;

    @Override
    public List<EmploymentQueryResponse> findInfoByEmployeeId(final Long employeeId) {
        return queryEmploymentRepository.findInfoByEmployeeId(employeeId);
    }

    @Override
    public boolean check(Long employeeId, Long employerId) {
        return queryEmploymentRepository.existsByEmployeeIdAndEmployerId(employeeId, employerId);
    }

}