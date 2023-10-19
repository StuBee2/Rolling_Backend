package com.stubee.rollingapi.domain.employment.request;

import com.stubee.employmentapplication.usecases.query.impl.queries.GetEmploymentExistenceQuery;

public record GetEmploymentExistenceRequest(
        Long employeeId,
        Long employerId) {
    public GetEmploymentExistenceQuery toQuery() {
        return new GetEmploymentExistenceQuery(employeeId, employerId);
    }
}