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

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class EmploymentDomainService implements RegisterEmploymentService, CheckEmploymentExistenceService {

    private final RegisterEmploymentPort registerEmploymentPort;
    private final CheckEmploymentExistencePort checkEmploymentExistencePort;

    @Override
    public Employment register(final RegisterEmploymentCommand command, final MemberId memberId) {
        if(checkEmploymentExistencePort.check(memberId.getId(), command.employerId())) {
            throw EmploymentExistException.EXCEPTION;
        }

        return registerEmploymentPort.register(command.toDomain(memberId));
    }

    @Override
    public void checkByEmployeeAndEmployer(final UUID employeeId, final UUID employerId) {
        if(!checkEmploymentExistencePort.check(employeeId, employerId)) {
            throw EmploymentNotFoundException.EXCEPTION;
        }
    }

}