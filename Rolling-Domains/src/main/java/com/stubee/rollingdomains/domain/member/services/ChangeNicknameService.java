package com.stubee.rollingdomains.domain.member.services;

import com.stubee.rollingdomains.domain.member.services.commands.ChangeNicknameCommand;

public interface ChangeNicknameService {

    void change(ChangeNicknameCommand command);

}