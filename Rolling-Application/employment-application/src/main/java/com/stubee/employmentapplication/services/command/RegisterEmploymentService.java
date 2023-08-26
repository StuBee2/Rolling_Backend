package com.stubee.employmentapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.company.CheckCompanyExistencePort;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.employmentapplication.commands.RegisterEmploymentCommand;
import com.stubee.employmentapplication.outports.CommandEmploymentPort;
import com.stubee.employmentapplication.usecases.command.RegisterEmploymentUseCase;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class RegisterEmploymentService implements RegisterEmploymentUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandEmploymentPort commandEmploymentPort;
    private final CheckCompanyExistencePort checkCompanyExistencePort;

    @Override
    public Employment register(final RegisterEmploymentCommand command) {
        doesCompanyExists(command.employerId());

        return commandEmploymentPort.register(command.toDomain(memberSecurityPort.getCurrentMemberId()));
    }

    private void doesCompanyExists(final UUID employerId) {
        if(checkCompanyExistencePort.check(employerId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

}