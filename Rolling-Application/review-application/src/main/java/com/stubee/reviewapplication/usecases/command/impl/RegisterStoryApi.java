package com.stubee.reviewapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.company.services.CheckCompanyExistenceService;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.story.services.WriteStoryService;
import com.stubee.rollingdomains.domain.story.services.commands.RegisterStoryCommand;
import com.stubee.reviewapplication.usecases.command.RegisterStoryUseCase;
import com.stubee.rollingdomains.domain.story.model.Story;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class RegisterStoryApi implements RegisterStoryUseCase {

    private final CheckCompanyExistenceService checkCompanyExistenceService;
    //private final CheckEmploymentExistenceService checkEmploymentExistenceService;
    private final GetCurrentMemberPort getCurrentMemberPort;
    private final WriteStoryService writeStoryService;

    @Override
    public Story register(final RegisterStoryCommand command) {
        checkCompanyExistenceService.checkById(command.companyId());

        final MemberId memberId = getCurrentMemberPort.getMemberId();

        //checkEmploymentExistenceService.checkByEmployeeAndEmployer(memberId.getId(), command.companyId());

        return writeStoryService.write(command, memberId);
    }

}

