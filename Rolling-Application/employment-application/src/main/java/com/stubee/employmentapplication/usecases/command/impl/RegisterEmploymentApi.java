package com.stubee.employmentapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyExistenceService;
import com.stubee.rollingdomains.domain.employment.services.RegisterEmploymentService;
import com.stubee.rollingdomains.domain.employment.services.commands.RegisterEmploymentCommand;
import com.stubee.employmentapplication.usecases.command.RegisterEmploymentUseCase;
import com.stubee.rollingdomains.domain.employment.model.Employment;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterEmploymentApi implements RegisterEmploymentUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final CheckCompanyExistenceService checkCompanyExistenceService;
    private final RegisterEmploymentService registerEmploymentService;

    @Override
    public Employment register(final RegisterEmploymentCommand command) {
        checkCompanyExistenceService.checkById(command.employerId());

        final MemberId memberId = getCurrentMemberPort.getMemberId();

        return registerEmploymentService.register(command, memberId);
    }

}