package com.stubee.employmentapplication.usecases.query;

import com.stubee.employmentapplication.usecases.query.impl.queries.GetEmploymentExistenceQuery;

public interface QueryEmploymentExistenceUseCase {

    boolean get(GetEmploymentExistenceQuery query);

}