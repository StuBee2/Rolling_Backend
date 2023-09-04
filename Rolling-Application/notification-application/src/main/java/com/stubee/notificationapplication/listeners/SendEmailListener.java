package com.stubee.notificationapplication.listeners;

import com.stubee.notificationapplication.outports.SendEmailPort;
import com.stubee.rollingdomains.domain.member.events.MemberRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SendEmailListener {

    private final SendEmailPort sendEmailPort;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = {MemberRegisteredEvent.class})
    public void sendWelcomeEmail(final MemberRegisteredEvent event) {
        sendEmailPort.sendWelcome(event.receiverEmail());
    }

}