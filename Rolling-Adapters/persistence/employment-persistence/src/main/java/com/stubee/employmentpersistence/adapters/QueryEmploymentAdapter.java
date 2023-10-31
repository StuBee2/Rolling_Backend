package com.stubee.employmentpersistence.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.employmentapplication.outports.QueryEmploymentByIdPort;
import com.stubee.employmentpersistence.repository.QueryEmploymentRepository;
import com.stubee.employmentapplication.usecases.query.response.EmploymentQueryResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
public class QueryEmploymentAdapter implements QueryEmploymentByIdPort, CheckEmploymentExistencePort {

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