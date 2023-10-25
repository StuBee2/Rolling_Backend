package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.member.services.ModifyNicknameService;
import com.stubee.rollingdomains.domain.member.services.commands.ModifyNicknameCommand;
import com.stubee.memberapplication.usecases.ModifyNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ModifyNicknameApi implements ModifyNicknameUseCase {

    private final ModifyNicknameService modifyNicknameService;

    @Override
    public void modify(final ModifyNicknameCommand command) {
        modifyNicknameService.modify(command);
    }

}