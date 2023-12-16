package com.stubee.employmentpersistence.adapters;

import com.stubee.employmentapplication.usecases.query.EmploymentQueryResponse;

import java.util.List;

interface QueryEmploymentRepository {

    List<EmploymentQueryResponse> findInfoByEmployeeId(Long employeeId);

    boolean existsByEmployeeIdAndEmployerId(Long employeeId, Long employerId);

}