package com.stubee.rollingdomains.domain.member.exception;

import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;

public class DuplicatedNicknameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedNicknameException();

    private DuplicatedNicknameException() {
        super(ErrorCode.DUPLICATED_NICKNAME);
    }

}