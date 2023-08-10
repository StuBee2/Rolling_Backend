package com.stubee.rollingservices.domain.employment.services.command;

import com.stubee.rollingcommons.commons.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingports.domain.company.ports.QueryCompanyPort;
import com.stubee.rollingports.domain.employment.ports.CommandEmploymentPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.employment.commands.RegisterEmploymentCommand;
import com.stubee.rollingusecases.domain.employment.usecases.command.RegisterEmploymentUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@CommandService
@RequiredArgsConstructor
public class RegisterEmploymentService implements RegisterEmploymentUseCase {

    private final MemberSecurityPort memberSecurityPort;
    private final CommandEmploymentPort commandEmploymentPort;
    private final QueryCompanyPort queryCompanyPort;

    @Override
    public Employment register(final RegisterEmploymentCommand command) {
        doesCompanyExists(command.employerId());

        return commandEmploymentPort.register(createExceptEmploymentId(command, memberSecurityPort.getCurrentMemberId()));
    }

    private void doesCompanyExists(final UUID employerId) {
        if(queryCompanyPort.existsByCompanyId(employerId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

    private Employment createExceptEmploymentId(final RegisterEmploymentCommand command, final MemberId employeeId) {
        return Employment.createExceptEmploymentId(employeeId, CompanyId.create(command.employerId()),
                EmploymentDetails.create(command.employmentStatus()));
    }

}