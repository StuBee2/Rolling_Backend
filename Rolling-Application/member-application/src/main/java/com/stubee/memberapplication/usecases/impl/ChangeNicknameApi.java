package com.stubee.memberapplication.usecases.impl;

import com.stubee.applicationcommons.annotations.CommandService;
import com.stubee.rollingdomains.domain.member.services.ChangeNicknameService;
import com.stubee.rollingdomains.domain.member.services.commands.ChangeNicknameCommand;
import com.stubee.memberapplication.usecases.ChangeNicknameUseCase;
import lombok.RequiredArgsConstructor;

@CommandService
@RequiredArgsConstructor
public class ChangeNicknameApi implements ChangeNicknameUseCase {

    private final ChangeNicknameService changeNicknameService;

    @Override
    public void update(final ChangeNicknameCommand command) {
        changeNicknameService.change(command);
    }

}