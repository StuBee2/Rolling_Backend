package com.stubee.employmentapplication.usecases.query.impl;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.employmentapplication.usecases.query.QueryEmploymentExistenceUseCase;
import com.stubee.employmentapplication.usecases.query.impl.queries.GetEmploymentExistenceQuery;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class QueryEmploymentExistenceApi implements QueryEmploymentExistenceUseCase {

    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    @Override
    public boolean get(final GetEmploymentExistenceQuery query) {
        return checkEmploymentExistencePort.check(query.employeeId(), query.employerId());
    }

}