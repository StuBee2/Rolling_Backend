package rolling.application.auth.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import rolling.application.auth.outport.CertifyAlumniPort;
import rolling.domain.member.events.MemberCertifiedEvent;

@Component
@RequiredArgsConstructor
public class CertifyAlumniUseCase {

    private final CertifyAlumniPort certifyAlumniPort;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void certify(final String housemaster) {
        certifyAlumniPort.certify(housemaster);

        applicationEventPublisher.publishEvent(new MemberCertifiedEvent());
    }

}