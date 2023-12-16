package com.stubee.emailnotification.adapters;

import com.stubee.adapterscommons.annotations.Adapter;
import com.stubee.notificationapplication.outports.SendEmailPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Adapter
@Slf4j
@RequiredArgsConstructor
class SendEmailAdapter implements SendEmailPort {

    private final JavaMailSender javaMailSender;
    private final EmailProperties emailProperties;

    @Override
    public void sendWelcome(final String receiver) {
        javaMailSender.send(getWelcomeMessage(receiver));
    }

    private SimpleMailMessage getWelcomeMessage(final String receiver) {
        log.info("Send Welcome Email to {}", receiver);

        final SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailProperties.getUsername());
        message.setTo(receiver);
        message.setSubject("Welcome");
        message.setText("Nice to meet you");

        return message;
    }

}