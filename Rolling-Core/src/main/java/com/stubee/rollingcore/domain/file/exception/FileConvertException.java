package com.stubee.rollingcore.domain.file.exception;

import com.stubee.rollingcore.common.exception.CustomException;
import com.stubee.rollingcore.common.exception.ErrorCode;

public class FileConvertException extends CustomException {

    public static final CustomException EXCEPTION = new FileConvertException();

    private FileConvertException() {
        super(ErrorCode.FILE_CONVERT_ERROR);
    }

}