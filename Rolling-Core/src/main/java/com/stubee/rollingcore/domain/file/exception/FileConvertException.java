package com.stubee.rollingcore.domain.file.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class FileConvertException extends CustomException {

    public static final CustomException EXCEPTION = new FileConvertException();

    private FileConvertException() {
        super(500, "File Convert Failed");
    }

}