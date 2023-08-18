package com.stubee.employmentapplication.usecases.query;

import com.stubee.rollingdomains.domain.employment.response.EmploymentQueryResponse;

import java.util.List;

public interface QueryMyEmploymentInfoListUseCase {

    List<EmploymentQueryResponse> get();

}