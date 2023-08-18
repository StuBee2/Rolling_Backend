package com.stubee.thirdparty.email.listener;

import com.stubee.rollingdomains.domain.email.model.SendWelcomeEmailEvent;
import com.stubee.thirdparty.common.properties.email.EmailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendEmailListener {

    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = {SendWelcomeEmailEvent.class})
    public void sendWelcomeEmail(final SendWelcomeEmailEvent event) {
        log.info("Send Welcome Email to {}", event.receiverEmail());

        javaMailSender.send(getWelcomeMessage(event));
    }

    private SimpleMailMessage getWelcomeMessage(final SendWelcomeEmailEvent event) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailProperties.getUsername());
        message.setTo(event.receiverEmail());
        message.setSubject(event.title());
        message.setText(event.content());

        return message;
    }

}