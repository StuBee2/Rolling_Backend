package com.stubee.memberapplication.services;

import com.stubee.applicationcommons.annotations.DomainService;
import com.stubee.memberapplication.outports.CheckNicknameDuplicationPort;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.rollingdomains.domain.member.exception.DuplicatedNicknameException;
import com.stubee.rollingdomains.domain.member.model.Member;
import com.stubee.applicationcommons.ports.GetCurrentMemberPort;
import com.stubee.rollingdomains.domain.member.services.ElevateMemberRoleService;
import com.stubee.rollingdomains.domain.member.services.ModifyNicknameService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
class MemberDomainService implements ModifyNicknameService, ElevateMemberRoleService {

    private final CommandMemberPort commandMemberPort;
    private final CheckNicknameDuplicationPort checkNicknameDuplicationPort;
    private final GetCurrentMemberPort getCurrentMemberPort;

    @Override
    public void modify(final String nickname) {
        this.checkByNickname(nickname);

        commandMemberPort.saveWithId(this.getMember().updateNickname(nickname));
    }

    private void checkByNickname(final String nickname) {
        if(checkNicknameDuplicationPort.check(nickname)) {
            throw DuplicatedNicknameException.EXCEPTION;
        }
    }

    @Override
    public void elevate() {
        commandMemberPort.saveWithId(this.getMember().elevateToMember());
    }

    private Member getMember() {
        return getCurrentMemberPort.getMember();
    }

}