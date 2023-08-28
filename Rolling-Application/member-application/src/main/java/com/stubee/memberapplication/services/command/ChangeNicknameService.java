package com.stubee.memberapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.applicationcommons.ports.LoadCurrentMemberPort;
import com.stubee.memberapplication.commands.ChangeNicknameCommand;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.memberapplication.usecases.ChangeNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeNicknameService implements ChangeNicknameUseCase {

    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandMemberPort commandMemberPort;

    @Override
    public void update(final ChangeNicknameCommand command) {
        commandMemberPort.saveWithId(loadCurrentMemberPort.getMember()
                .changeNickname(command.nickName()));
    }

}