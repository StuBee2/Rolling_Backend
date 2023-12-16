package com.stubee.rollingapi.domain.employment;

import com.stubee.employmentapplication.usecases.query.impl.GetEmploymentExistenceQuery;

record GetEmploymentExistenceRequest(
        Long employeeId,
        Long employerId) {
    public GetEmploymentExistenceQuery toQuery() {
        return new GetEmploymentExistenceQuery(employeeId, employerId);
    }
}