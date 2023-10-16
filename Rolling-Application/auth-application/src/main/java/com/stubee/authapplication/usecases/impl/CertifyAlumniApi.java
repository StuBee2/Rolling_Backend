package com.stubee.authapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.authapplication.outports.CertifyAlumniPort;
import com.stubee.authapplication.usecases.CertifyAlumniUseCase;
import com.stubee.rollingdomains.domain.member.events.MemberCertifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@CommandService
@RequiredArgsConstructor
public class CertifyAlumniApi implements CertifyAlumniUseCase {

    private final CertifyAlumniPort certifyAlumniPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void certify(final String housemaster) {
        certifyAlumniPort.certify(housemaster);

        applicationEventPublisher.publishEvent(new MemberCertifiedEvent());
    }

}