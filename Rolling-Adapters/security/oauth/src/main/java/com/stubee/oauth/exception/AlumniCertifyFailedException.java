package com.stubee.oauth.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class AlumniCertifyFailedException extends CustomException {

    public static final CustomException EXCEPTION = new AlumniCertifyFailedException();

    private AlumniCertifyFailedException() {
        super(ErrorCode.ALUMNI_CERTIFY_FAILED);
    }

}