package com.stubee.employmentapplication.outports;

import com.stubee.employmentapplication.usecases.query.EmploymentQueryResponse;

import java.util.List;

public interface QueryEmploymentByIdPort {

    List<EmploymentQueryResponse> findInfoByEmployeeId(Long employeeId);

}