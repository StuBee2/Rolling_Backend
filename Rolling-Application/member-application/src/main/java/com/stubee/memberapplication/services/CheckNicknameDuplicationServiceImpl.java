package com.stubee.memberapplication.services;

import com.stubee.applicationcommons.annotations.QueryService;
import com.stubee.memberapplication.outports.CheckNicknameDuplicationPort;
import com.stubee.rollingdomains.domain.member.exception.DuplicatedNicknameException;
import com.stubee.rollingdomains.domain.member.services.CheckNicknameDuplicationService;
import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class CheckNicknameDuplicationServiceImpl implements CheckNicknameDuplicationService {

    private final CheckNicknameDuplicationPort checkNicknameDuplicationPort;

    public void check(final String nickname) {
        if(checkNicknameDuplicationPort.check(nickname)) {
            throw DuplicatedNicknameException.EXCEPTION;
        }
    }

}