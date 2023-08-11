package com.stubee.rollingservices.domain.member.services.command;

import com.stubee.rollingcommons.commons.annotations.CommandService;
import com.stubee.rollingports.domain.member.ports.CommandMemberPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.member.commands.ChangeNicknameCommand;
import com.stubee.rollingusecases.domain.member.usecases.ChangeNicknameUseCase;
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