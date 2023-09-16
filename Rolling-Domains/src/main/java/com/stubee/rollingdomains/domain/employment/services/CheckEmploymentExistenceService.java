package com.stubee.rollingdomains.domain.employment.services;

import java.util.UUID;

public interface CheckEmploymentExistenceService {

    void checkByEmployeeAndEmployer(UUID employeeId, UUID employerId);

}