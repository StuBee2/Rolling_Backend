package com.stubee.rollingcore.domain.member.exception;

import com.stubee.rollingcore.common.exception.CustomException;

public class MemberNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberNotFoundException();

    private MemberNotFoundException() {
        super(404, "Member Not Found");
    }

}