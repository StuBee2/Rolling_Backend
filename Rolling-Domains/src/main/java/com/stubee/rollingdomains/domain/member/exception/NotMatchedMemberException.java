package com.stubee.rollingdomains.domain.member.exception;

import com.stubee.rollingdomains.common.exception.CustomException;
import com.stubee.rollingdomains.common.exception.ErrorCode;

public class NotMatchedMemberException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedMemberException();

    private NotMatchedMemberException() {
        super(ErrorCode.NOT_MATCHED_MEMBER);
    }

}