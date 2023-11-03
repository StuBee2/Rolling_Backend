package com.stubee.loggingapplication.services;

import com.stubee.applicationcommons.annotations.AsyncEventListener;
import com.stubee.applicationcommons.annotations.Listener;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.domain.company.events.CompanyViewedEvent;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.domain.logging.model.CompanyViewLogging;
import lombok.RequiredArgsConstructor;

@Listener
@RequiredArgsConstructor
public class CompanyViewLoggingService {

    private final CommandLoggingPort<CompanyViewLogging> commandLoggingPort;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @AsyncEventListener
    public void pileUp(final CompanyViewedEvent event) {
        commandLoggingPort.save(CompanyViewLogging.ExceptIdBuilder()
                .memberId(getCurrentMemberPort.getMemberId())
                .companyId(CompanyId.of(event.companyId()))
                .build());
    }

}