package com.stubee.memberapplication.usecases.command.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.member.services.ModifyNicknameService;
import com.stubee.memberapplication.usecases.command.ModifyNicknameCommand;
import com.stubee.memberapplication.usecases.command.ModifyNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyNicknameApi implements ModifyNicknameUseCase {

    private final ModifyNicknameService modifyNicknameService;

    @Override
    public void modify(final ModifyNicknameCommand command) {
        modifyNicknameService.modify(command.nickname());
    }

}