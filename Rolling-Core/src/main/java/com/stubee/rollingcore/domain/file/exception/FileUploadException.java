package com.stubee.rollingcore.domain.file.exception;

import com.stubee.rollingcore.common.exception.CustomException;
import com.stubee.rollingcore.common.exception.ErrorCode;

public class FileUploadException extends CustomException {

    public static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}