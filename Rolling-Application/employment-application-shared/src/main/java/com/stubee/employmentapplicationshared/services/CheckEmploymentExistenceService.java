package com.stubee.employmentapplicationshared.services;

import java.util.UUID;

public interface CheckEmploymentExistenceService {

    void check(UUID employeeId, UUID employerId);

}