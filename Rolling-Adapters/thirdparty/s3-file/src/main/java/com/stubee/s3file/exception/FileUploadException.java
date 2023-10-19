package com.stubee.s3file.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class FileUploadException extends CustomException {

    public static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}