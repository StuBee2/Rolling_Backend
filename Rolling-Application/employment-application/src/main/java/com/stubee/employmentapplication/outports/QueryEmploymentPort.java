package com.stubee.employmentapplication.outports;

import com.stubee.employmentapplication.services.query.response.EmploymentQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryEmploymentPort {

    List<EmploymentQueryResponse> findInfoByEmployeeId(UUID employeeId);

}