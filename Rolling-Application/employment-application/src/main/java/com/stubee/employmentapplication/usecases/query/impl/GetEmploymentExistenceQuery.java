package com.stubee.employmentapplication.usecases.query.impl;

public record GetEmploymentExistenceQuery(
        Long employeeId,
        Long employerId) {}