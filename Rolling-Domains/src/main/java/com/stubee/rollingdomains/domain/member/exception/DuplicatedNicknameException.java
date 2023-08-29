package com.stubee.rollingdomains.domain.member.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class DuplicatedNicknameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedNicknameException();

    private DuplicatedNicknameException() {
        super(ErrorCode.DUPLICATED_NICKNAME);
    }

}