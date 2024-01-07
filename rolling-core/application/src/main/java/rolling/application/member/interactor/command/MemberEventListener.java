package rolling.application.member.interactor.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rolling.application.member.outport.CommandMemberPort;
import rolling.application.member.outport.MemberSessionPort;
import rolling.domain.member.events.MemberCertifiedEvent;
import rolling.domain.member.model.Member;

@Component
@RequiredArgsConstructor
class MemberEventListener {

    private final MemberSessionPort memberSessionPort;
    private final CommandMemberPort commandMemberPort;

    @Async
    @Transactional
    @EventListener
    public void listen(final MemberCertifiedEvent event) {
        final Member member = memberSessionPort.current();

        member.elevateToMember();

        commandMemberPort.saveWithId(member);
    }

}