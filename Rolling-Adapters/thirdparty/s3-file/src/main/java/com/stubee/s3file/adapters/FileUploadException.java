package com.stubee.s3file.adapters;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

class FileUploadException extends CustomException {

    static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}