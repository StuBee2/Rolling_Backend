package com.stubee.rollingapplication.domain.member.port.api;

import com.stubee.rollingcore.domain.member.command.UpdateNickNameCommand;

public interface UpdateNicknameUseCase {

    void update(UpdateNickNameCommand command);

}