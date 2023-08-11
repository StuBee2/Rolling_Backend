package com.stubee.rollingusecases.domain.member.usecases;

import com.stubee.rollingusecases.domain.member.commands.ChangeNicknameCommand;

public interface ChangeNicknameUseCase {

    void update(ChangeNicknameCommand command);

}