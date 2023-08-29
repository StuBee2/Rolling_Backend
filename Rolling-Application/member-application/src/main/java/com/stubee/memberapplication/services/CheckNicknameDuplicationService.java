package com.stubee.memberapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.outports.CheckNicknameDuplicationPort;
import com.stubee.rollingdomains.domain.member.exception.DuplicatedNicknameException;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class CheckNicknameDuplicationService {

    private final CheckNicknameDuplicationPort checkNicknameDuplicationPort;

    public void check(final String nickname) {
        if(checkNicknameDuplicationPort.check(nickname)) {
            throw DuplicatedNicknameException.EXCEPTION;
        }
    }

}