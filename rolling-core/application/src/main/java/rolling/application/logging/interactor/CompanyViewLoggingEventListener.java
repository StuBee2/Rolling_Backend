package rolling.application.logging.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.logging.outport.CommandLoggingPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.company.events.CompanyViewedEvent;
import rolling.domain.company.model.CompanyId;
import rolling.domain.logging.model.CompanyViewLogging;
import rolling.domain.member.model.MemberId;

@Component
@RequiredArgsConstructor
class CompanyViewLoggingEventListener {

    private final CommandLoggingPort<CompanyViewLogging> commandLoggingPort;
    private final MemberSessionPort memberSessionPort;

    @Async
    @Transactional
    @EventListener
    public void listen(final CompanyViewedEvent event) {
        MemberId memberId;

        try {
            memberId = memberSessionPort.currentId();
        } catch (Exception e) {
            memberId = MemberId.of(-1L);
        }

        commandLoggingPort.save(CompanyViewLogging.ExceptIdBuilder()
                .memberId(memberId)
                .companyId(CompanyId.of(event.companyId()))
                .build());
    }

}