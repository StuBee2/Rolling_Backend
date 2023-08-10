package com.stubee.rollingexternal.persistence.domain.employment.repository;

import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryEmploymentRepository {

    List<EmploymentQueryResponse> findInfoByEmployeeId(UUID employeeId);

}