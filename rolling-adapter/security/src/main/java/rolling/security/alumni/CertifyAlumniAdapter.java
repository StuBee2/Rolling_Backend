package rolling.security.alumni;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.auth.outport.CertifyAlumniPort;

@Component
@RequiredArgsConstructor
class CertifyAlumniAdapter implements CertifyAlumniPort {

    private final CertifyProperties certifyProperties;

    @Override
    public void certify(final String housemaster) {
        if(!certifyProperties.getHousemasterList().contains(housemaster)) {
            throw AlumniCertifyFailedException.EXCEPTION;
        }
    }

}