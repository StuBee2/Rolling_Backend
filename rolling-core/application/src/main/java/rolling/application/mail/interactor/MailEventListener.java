package rolling.application.mail.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import rolling.application.mail.outport.SendMailPort;
import rolling.domain.member.events.MemberRegisteredEvent;

@Component
@RequiredArgsConstructor
class MailEventListener {

    private final SendMailPort sendEmailPort;

    @Async
    @Transactional(propagation = Propagation.NEVER)
    @TransactionalEventListener
    public void listen(final MemberRegisteredEvent event) {
        sendEmailPort.sendWelcome(event.receiverEmail());
    }

}