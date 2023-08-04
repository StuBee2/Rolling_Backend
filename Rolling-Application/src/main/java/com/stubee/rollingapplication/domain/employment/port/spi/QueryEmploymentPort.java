package com.stubee.rollingapplication.domain.employment.port.spi;

import com.stubee.rollingcore.domain.employment.response.EmploymentQueryResponse;

import java.util.List;
import java.util.UUID;

public interface QueryEmploymentPort {

    List<EmploymentQueryResponse> findInfoByEmployeeId(UUID employeeId);

}