package com.stubee.loggingapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.logging.services.PileUpLoggingService;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.loggingapplication.usecases.PileUpLoggingUseCase;
import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class PileUpLoggingApi implements PileUpLoggingUseCase {

    private final GetCurrentMemberPort getCurrentMemberPort;
    private final PileUpLoggingService pileUpLoggingService;

    @Override
    public Logging pileUp(final PileUpLoggingCommand command) {
        final MemberId memberId = getCurrentMemberPort.getMemberId();

        return pileUpLoggingService.pileUp(command, memberId);
    }

}