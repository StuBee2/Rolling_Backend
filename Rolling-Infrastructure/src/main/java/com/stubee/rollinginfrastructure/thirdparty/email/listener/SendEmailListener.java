package com.stubee.rollinginfrastructure.thirdparty.email.listener;

import com.stubee.rollingcore.domain.email.model.SendWelcomeEmailEvent;
import com.stubee.rollinginfrastructure.thirdparty.email.properties.EmailProperties;
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
        try {
            Thread.sleep(10);
            javaMailSender.send(getWelcomeMessage(event));
        } catch (InterruptedException e) {
            log.info("SendEmailAdapter Exception : {}", e.getMessage());
        }
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