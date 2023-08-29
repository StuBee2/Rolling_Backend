package com.stubee.employmentpersistence.repository;

import com.stubee.employmentapplication.services.query.response.EmploymentQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryEmploymentRepository {

    List<EmploymentQueryResponse> findInfoByEmployeeId(UUID employeeId);

    boolean existsByEmployeeIdAndEmployerId(UUID employeeId, UUID employerId);

}