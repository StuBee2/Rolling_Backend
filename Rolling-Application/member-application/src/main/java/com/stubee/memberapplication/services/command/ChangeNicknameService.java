package com.stubee.memberapplication.services.command;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.memberapplication.commands.ChangeNicknameCommand;
import com.stubee.memberapplication.outports.CommandMemberPort;
import com.stubee.memberapplication.services.CheckNicknameDuplicationServiceImpl;
import com.stubee.memberapplication.usecases.ChangeNicknameUseCase;
import com.stubee.rollingdomains.domain.member.ports.LoadCurrentMemberPort;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeNicknameService implements ChangeNicknameUseCase {

    private final CheckNicknameDuplicationServiceImpl checkNicknameDuplicationService;
    private final LoadCurrentMemberPort loadCurrentMemberPort;
    private final CommandMemberPort commandMemberPort;

    @Override
    public void update(final ChangeNicknameCommand command) {
        checkNicknameDuplicationService.check(command.nickname());

        commandMemberPort.saveWithId(loadCurrentMemberPort.getMember()
                .changeNickname(command.nickname()));
    }

}