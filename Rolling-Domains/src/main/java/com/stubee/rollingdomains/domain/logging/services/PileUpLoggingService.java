package com.stubee.rollingdomains.domain.logging.services;

import com.stubee.rollingdomains.domain.logging.model.Logging;
import com.stubee.rollingdomains.domain.logging.services.commands.PileUpLoggingCommand;
import com.stubee.rollingdomains.domain.member.model.MemberId;

public interface PileUpLoggingService {

    Logging pileUp(PileUpLoggingCommand command, MemberId memberId);

}