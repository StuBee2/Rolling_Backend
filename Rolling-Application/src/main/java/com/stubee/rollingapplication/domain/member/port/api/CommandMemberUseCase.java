package com.stubee.rollingapplication.domain.member.port.api;

import com.stubee.rollingapplication.domain.member.command.UpdateNickNameCommand;

public interface CommandMemberUseCase {

    void updateNickName(UpdateNickNameCommand command);

}