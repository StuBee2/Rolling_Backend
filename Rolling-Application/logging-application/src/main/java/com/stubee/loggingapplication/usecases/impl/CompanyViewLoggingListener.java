package com.stubee.loggingapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.AsyncEventListener;
import com.stubee.applicationcommons.annotations.Listener;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.loggingapplication.outports.CommandLoggingPort;
import com.stubee.rollingdomains.domain.company.events.CompanyViewedEvent;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import com.stubee.rollingdomains.logging.model.CompanyViewLogging;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import lombok.RequiredArgsConstructor;

@Listener
@RequiredArgsConstructor
class CompanyViewLoggingListener {

    private final CommandLoggingPort<CompanyViewLogging> commandLoggingPort;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @AsyncEventListener
    public void pileUp(final CompanyViewedEvent event) {
        MemberId memberId;

        try {
            memberId = getCurrentMemberPort.getMemberId();
        } catch (Exception e) {
            memberId = MemberId.of(-1L);
        }

        commandLoggingPort.save(CompanyViewLogging.ExceptIdBuilder()
                .memberId(memberId)
                .companyId(CompanyId.of(event.companyId()))
                .build());
    }

}