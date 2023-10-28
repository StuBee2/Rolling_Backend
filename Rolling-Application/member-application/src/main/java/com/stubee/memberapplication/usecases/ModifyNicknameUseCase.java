package com.stubee.memberapplication.usecases;

import com.stubee.rollingdomains.domain.member.services.commands.ModifyNicknameCommand;

public interface ModifyNicknameUseCase {

    void modify(ModifyNicknameCommand command);

}