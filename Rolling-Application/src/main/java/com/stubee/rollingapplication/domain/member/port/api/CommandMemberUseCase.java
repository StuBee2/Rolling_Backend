package com.stubee.rollingapplication.domain.member.port.api;

import com.stubee.rollingcore.domain.member.dto.command.UpdateNickNameCommand;

public interface CommandMemberUseCase {

    void updateNickName(UpdateNickNameCommand command);

}