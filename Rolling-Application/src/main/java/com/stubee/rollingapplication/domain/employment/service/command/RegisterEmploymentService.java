package com.stubee.rollingapplication.domain.employment.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.company.port.spi.QueryCompanyPort;
import com.stubee.rollingapplication.domain.employment.port.api.command.RegisterEmploymentUseCase;
import com.stubee.rollingapplication.domain.employment.port.spi.CommandEmploymentPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.company.exception.CompanyNotFoundException;
import com.stubee.rollingcore.domain.company.model.CompanyId;
import com.stubee.rollingcore.domain.employment.command.RegisterEmploymentCommand;
import com.stubee.rollingcore.domain.employment.model.Employment;
import com.stubee.rollingcore.domain.employment.model.EmploymentDetails;
import com.stubee.rollingcore.domain.member.model.MemberId;
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