package com.stubee.employmentapplication.usecases.query.impl.queries;

public record GetEmploymentExistenceQuery(
        Long employeeId,
        Long employerId) {}