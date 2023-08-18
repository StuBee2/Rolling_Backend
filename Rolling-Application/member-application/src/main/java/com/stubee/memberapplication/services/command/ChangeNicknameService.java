package com.stubee.memberapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.member.MemberSecurityPort;
import com.stubee.memberapplication.commands.ChangeNicknameCommand;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.memberapplication.usecases.ChangeNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeNicknameService implements ChangeNicknameUseCase {

    private final MemberSecurityPort securityPort;
    private final CommandMemberPort commandMemberPort;

    @Override
    public void update(ChangeNicknameCommand command) {
        commandMemberPort.saveWithId(securityPort.getCurrentMember()
                .changeNickname(command.nickName()));
    }

}