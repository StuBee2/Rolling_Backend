package com.stubee.rollingservices.domain.member.services.command;

import com.stubee.rollingcommons.commons.annotations.CommandService;
import com.stubee.rollingports.domain.member.ports.CommandMemberPort;
import com.stubee.rollingports.domain.member.ports.MemberSecurityPort;
import com.stubee.rollingusecases.domain.member.commands.UpdateNickNameCommand;
import com.stubee.rollingusecases.domain.member.usecases.UpdateNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class UpdateNicknameService implements UpdateNicknameUseCase {

    private final MemberSecurityPort securityPort;
    private final CommandMemberPort commandMemberPort;

    @Override
    public void update(UpdateNickNameCommand command) {
        commandMemberPort.saveWithId(securityPort.getCurrentMember()
                .updateNickname(command.nickName()));
    }

}