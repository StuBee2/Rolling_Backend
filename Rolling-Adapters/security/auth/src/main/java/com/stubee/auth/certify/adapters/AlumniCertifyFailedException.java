package com.stubee.auth.certify.adapters;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

class AlumniCertifyFailedException extends CustomException {

    static final CustomException EXCEPTION = new AlumniCertifyFailedException();

    private AlumniCertifyFailedException() {
        super(ErrorCode.ALUMNI_CERTIFY_FAILED);
    }

}