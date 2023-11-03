package com.stubee.employmentpersistence.repository;

import com.stubee.employmentapplication.usecases.query.EmploymentQueryResponse;

import java.util.List;

public interface QueryEmploymentRepository {

    List<EmploymentQueryResponse> findInfoByEmployeeId(Long employeeId);

    boolean existsByEmployeeIdAndEmployerId(Long employeeId, Long employerId);

}