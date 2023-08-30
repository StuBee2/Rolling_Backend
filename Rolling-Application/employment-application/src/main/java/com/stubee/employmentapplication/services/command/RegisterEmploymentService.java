package com.stubee.employmentapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.companyshared.services.CheckCompanyExistenceService;
import com.stubee.employmentapplication.commands.RegisterEmploymentCommand;
import com.stubee.employmentapplication.outports.CommandEmploymentPort;
import com.stubee.employmentapplication.usecases.command.RegisterEmploymentUseCase;
import com.stubee.employmentshared.services.CheckEmploymentExistenceService;
import com.stubee.membershared.ports.LoadCurrentMemberPort;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterEmploymentService implements RegisterEmploymentUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final CheckEmploymentExistenceService checkEmploymentExistenceService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandEmploymentPort commandEmploymentPort;

    @Override
    public Employment register(final RegisterEmploymentCommand command) {
        checkCompanyExistenceService.check(command.employerId());

        final MemberId employeeId = loadCurrentMemberPort.getMemberId();

        checkEmploymentExistenceService.check(employeeId.getId(), command.employerId());

        return commandEmploymentPort.register(command.toDomain(employeeId));
    }

}