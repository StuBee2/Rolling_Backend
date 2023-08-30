package com.stubee.employmentshared.services;

import java.util.UUID;

public interface CheckEmploymentExistenceService {

    void check(UUID employeeId, UUID employerId);

}