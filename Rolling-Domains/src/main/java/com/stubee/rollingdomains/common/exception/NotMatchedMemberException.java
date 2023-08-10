package com.stubee.rollingdomains.common.exception;

public class NotMatchedMemberException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedMemberException();

    private NotMatchedMemberException() {
        super(ErrorCode.NOT_MATCHED_MEMBER);
    }

}