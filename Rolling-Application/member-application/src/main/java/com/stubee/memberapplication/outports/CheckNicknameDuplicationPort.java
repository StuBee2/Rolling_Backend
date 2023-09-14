package com.stubee.memberapplication.outports;

import com.stubee.rollingdomains.domain.member.exception.DuplicatedNicknameException;

public interface CheckNicknameDuplicationPort {

    boolean check (String nickname);

    default void checkByNickname(final String nickname) {
        if(check(nickname)) {
            throw DuplicatedNicknameException.EXCEPTION;
        }
    }

}