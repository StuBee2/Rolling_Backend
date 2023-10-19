package com.stubee.employmentapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.employmentapplication.outports.CheckEmploymentExistencePort;
import com.stubee.employmentapplication.outports.RegisterEmploymentPort;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentExistException;
import com.stubee.rollingdomains.domain.employment.exception.EmploymentNotFoundException;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.services.CheckEmploymentExistenceService;
import com.stubee.rollingdomains.domain.employment.services.RegisterEmploymentService;
import com.stubee.rollingdomains.domain.employment.services.commands.RegisterEmploymentCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class EmploymentDomainService implements RegisterEmploymentService, CheckEmploymentExistenceService {

    private final RegisterEmploymentPort registerEmploymentPort;
    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    @Override
    public Employment register(final RegisterEmploymentCommand command, final MemberId memberId) {
        if(this.checkExistence(memberId.getId(), command.employerId())) {
            throw EmploymentExistException.EXCEPTION;
        }

        return registerEmploymentPort.register(command.toDomain(memberId));
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

}