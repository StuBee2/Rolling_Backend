package com.stubee.employmentapplication.usecases.query.impl.queries;

import java.util.UUID;

public record GetEmploymentExistenceQuery(
        UUID employeeId,
        UUID employerId) {}