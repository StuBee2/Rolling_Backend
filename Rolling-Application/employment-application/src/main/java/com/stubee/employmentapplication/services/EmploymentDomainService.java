package com.stubee.employmentapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.employmentapplication.outports.RegisterEmploymentPort;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentExistException;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentNotFoundException;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.employment.services.CheckEmploymentExistenceService;
import com.stubee.rollingdomains.domain.employment.services.RegisterEmploymentService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
class EmploymentDomainService implements RegisterEmploymentService, CheckEmploymentExistenceService {

    private final RegisterEmploymentPort registerEmploymentPort;
    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    @Override
    public Employment register(final Employment employment) {
        if(this.checkExistence(employment.employmentDetails())) {
            throw EmploymentExistException.EXCEPTION;
        }

        return registerEmploymentPort.register(employment);
    }

    @Override
    public void checkByEmployeeAndEmployer(final Long employeeId, final Long employerId) {
        if(!this.checkExistence(employeeId, employerId)) {
            throw EmploymentNotFoundException.EXCEPTION;
        }
    }

    private boolean checkExistence(final Long employeeId, final Long employerId) {
        return checkEmploymentExistencePort.check(employeeId, employerId);
    }

    private boolean checkExistence(final EmploymentDetails employmentDetails) {
        return checkEmploymentExistencePort.check(employmentDetails.employeeId().getId(), employmentDetails.employerId().getId());
    }

}