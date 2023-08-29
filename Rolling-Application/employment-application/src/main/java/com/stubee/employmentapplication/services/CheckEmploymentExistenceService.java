package com.stubee.employmentapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentExistException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class CheckEmploymentExistenceService {

    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    public void check(UUID employeeId, UUID employerId) {
        if(checkEmploymentExistencePort.check(employeeId, employerId)) {
            throw EmploymentExistException.EXCEPTION;
        }
    }

}