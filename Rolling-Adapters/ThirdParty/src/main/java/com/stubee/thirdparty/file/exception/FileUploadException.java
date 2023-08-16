package com.stubee.thirdparty.file.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class FileUploadException extends CustomException {

    public static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}