package com.stubee.employmentapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentExistException;
import com.stubee.rollingdomains.domain.employment.services.CheckEmploymentExistenceService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@QueryService
@RequiredArgsConstructor
public class CheckEmploymentExistenceServiceImpl implements CheckEmploymentExistenceService {

    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    @Override
    public void check(final UUID employeeId, final UUID employerId) {
        if(checkEmploymentExistencePort.check(employeeId, employerId)) {
            throw EmploymentExistException.EXCEPTION;
        }
    }

}