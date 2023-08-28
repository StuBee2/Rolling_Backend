package com.stubee.employmentapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.LoadCurrentMemberPort;
import com.stubee.applicationcommons.services.CheckCompanyExistenceService;
import com.stubee.employmentapplication.commands.RegisterEmploymentCommand;
import com.stubee.employmentapplication.outports.CommandEmploymentPort;
import com.stubee.employmentapplication.usecases.command.RegisterEmploymentUseCase;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterEmploymentService implements RegisterEmploymentUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandEmploymentPort commandEmploymentPort;

    @Override
    public Employment register(final RegisterEmploymentCommand command) {
        checkCompanyExistenceService.check(command.employerId());

        return commandEmploymentPort.register(command.toDomain(loadCurrentMemberPort.getMemberId()));
    }

}