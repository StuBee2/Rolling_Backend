package com.stubee.employmentapplication.usecases.query;

import com.stubee.employmentapplication.usecases.query.impl.GetEmploymentExistenceQuery;

public interface QueryEmploymentExistenceUseCase {

    boolean get(GetEmploymentExistenceQuery query);

}