package com.stubee.memberapplication.usecases;

import com.stubee.rollingdomains.domain.member.services.commands.ChangeNicknameCommand;

public interface ChangeNicknameUseCase {

    void update(ChangeNicknameCommand command);

}