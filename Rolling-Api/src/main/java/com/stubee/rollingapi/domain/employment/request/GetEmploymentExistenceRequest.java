package com.stubee.rollingapi.domain.employment.request;

import com.stubee.employmentapplication.usecases.query.impl.queries.GetEmploymentExistenceQuery;

import java.util.UUID;

public record GetEmploymentExistenceRequest(
        UUID employeeId,
        UUID employerId) {
    public GetEmploymentExistenceQuery toQuery() {
        return new GetEmploymentExistenceQuery(employeeId, employerId);
    }
}