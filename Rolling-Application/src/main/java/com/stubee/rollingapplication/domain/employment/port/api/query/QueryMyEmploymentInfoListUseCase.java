package com.stubee.rollingapplication.domain.employment.port.api.query;

import com.stubee.rollingcore.domain.employment.response.EmploymentQueryResponse;

import java.util.List;

public interface QueryMyEmploymentInfoListUseCase {

    List<EmploymentQueryResponse> get();

}