package com.stubee.loggingapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.logging.services.PileUpLoggingService;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingApi implements PileUpLoggingUseCase {

    private final GetMemberInfoService getMemberInfoService;
    private final PileUpLoggingService pileUpLoggingService;

    @Override
    public Logging pileUp(final PileUpLoggingCommand command) {
        final MemberId memberId = getMemberInfoService.getMemberId();

        return pileUpLoggingService.pileUp(command, memberId);
    }

}