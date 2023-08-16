package com.stubee.rollingservices.domain.employment.services.command;

import com.stubee.rollingdomains.domain.employment.model.EmployeeId;
import com.stubee.rollingdomains.domain.employment.model.EmployerId;
import com.stubee.rollingservices.common.annotations.CommandService;
import com.stubee.rollingdomains.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.employment.model.EmploymentDetails;
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

        return commandEmploymentPort.register(createExceptEmploymentId(command, memberSecurityPort.getCurrentMemberId().getId()));
    }

    private void doesCompanyExists(final UUID employerId) {
        if(queryCompanyPort.existsByCompanyId(employerId)) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }

    private Employment createExceptEmploymentId(final RegisterEmploymentCommand command, final UUID employeeId) {
        return Employment.createExceptEmploymentId(EmployeeId.create(employeeId), EmployerId.create(command.employerId()),
                EmploymentDetails.create(command.employmentStatus()));
    }

}