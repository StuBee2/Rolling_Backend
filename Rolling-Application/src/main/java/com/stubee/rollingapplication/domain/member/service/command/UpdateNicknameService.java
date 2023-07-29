package com.stubee.rollingapplication.domain.member.service.command;

import com.stubee.rollingapplication.common.annotation.CommandService;
import com.stubee.rollingapplication.domain.member.port.api.UpdateNicknameUseCase;
import com.stubee.rollingapplication.domain.member.port.spi.CommandMemberPort;
import com.stubee.rollingapplication.domain.member.port.spi.MemberSecurityPort;
import com.stubee.rollingcore.domain.member.command.UpdateNickNameCommand;
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