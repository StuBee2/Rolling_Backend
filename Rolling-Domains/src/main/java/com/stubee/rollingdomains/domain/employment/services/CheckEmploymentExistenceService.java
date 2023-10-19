package com.stubee.rollingdomains.domain.employment.services;

public interface CheckEmploymentExistenceService {

    void checkByEmployeeAndEmployer(Long employeeId, Long employerId);

}