package com.stubee.rollingdomains.domain.employment.services;

import java.util.UUID;

public interface CheckEmploymentExistenceService {

    void check(UUID employeeId, UUID employerId);

}