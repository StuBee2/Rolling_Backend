package com.stubee.memberapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.memberapplication.outports.CheckNicknameDuplicationPort;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.memberapplication.outports.QueryMemberPort;
import com.stubee.rollingdomains.domain.member.exception.DuplicatedNicknameException;
import com.stubee.rollingdomains.domain.member.exception.MemberNotFoundException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.memberapplication.outports.LoadCurrentMemberPort;
import com.stubee.rollingdomains.domain.member.model.MemberId;
import com.stubee.rollingdomains.domain.member.services.ChangeNicknameService;
import com.stubee.rollingdomains.domain.member.services.GetMemberByIdService;
import com.stubee.rollingdomains.domain.member.services.GetMemberInfoService;
import com.stubee.rollingdomains.domain.member.services.commands.ChangeNicknameCommand;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class MemberDomainService implements ChangeNicknameService, GetMemberByIdService, GetMemberInfoService {

    private final CommandMemberPort commandMemberPort;
    private final QueryMemberPort queryMemberPort;
    private final CheckNicknameDuplicationPort checkNicknameDuplicationPort;
    private final LoadCurrentMemberPort loadCurrentMemberPort;

    @Override
    public Member getById(final UUID id) {
        return queryMemberPort.findById(id)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }

    @Override
    public Member getMember() {
        return loadCurrentMemberPort.getMember();
    }

    @Override
    public MemberId getMemberId() {
        return loadCurrentMemberPort.getMemberId();
    }

    @Override
    public void change(final ChangeNicknameCommand command) {
        if(checkNicknameDuplicationPort.check(command.nickname())) {
            throw DuplicatedNicknameException.EXCEPTION;
        }

        commandMemberPort.saveWithId(this.getMember().changeNickname(command.nickname()));
    }

}