package com.stubee.rollingusecases.domain.member.usecases;

import com.stubee.rollingusecases.domain.member.commands.UpdateNickNameCommand;

public interface UpdateNicknameUseCase {

    void update(UpdateNickNameCommand command);

}