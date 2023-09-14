package com.stubee.employmentapplication.outports;

import com.stubee.rollingdomains.domain.employment.exception.EmploymentExistException;

import java.util.UUID;

public interface CheckEmploymentExistencePort {

    boolean check(UUID employeeId, UUID employerId);

    default void checkByEmployeeAndEmployer(final UUID employeeId, final UUID employerId) {
        if(check(employeeId, employerId)) {
            throw EmploymentExistException.EXCEPTION;
        }
    }

}