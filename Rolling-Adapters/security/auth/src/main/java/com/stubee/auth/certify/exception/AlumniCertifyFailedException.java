package com.stubee.auth.certify.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class AlumniCertifyFailedException extends CustomException {

    public static final CustomException EXCEPTION = new AlumniCertifyFailedException();

    private AlumniCertifyFailedException() {
        super(ErrorCode.ALUMNI_CERTIFY_FAILED);
    }

}