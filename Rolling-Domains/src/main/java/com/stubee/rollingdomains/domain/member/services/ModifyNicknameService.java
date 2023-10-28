package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.services.commands.ModifyNicknameCommand;

public interface ModifyNicknameService {

    void modify(ModifyNicknameCommand command);

}