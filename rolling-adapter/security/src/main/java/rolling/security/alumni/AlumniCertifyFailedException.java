package rolling.security.alumni;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

class AlumniCertifyFailedException extends CustomException {

    static final CustomException EXCEPTION = new AlumniCertifyFailedException();

    private AlumniCertifyFailedException() {
        super(ErrorCode.ALUMNI_CERTIFY_FAILED);
    }

}