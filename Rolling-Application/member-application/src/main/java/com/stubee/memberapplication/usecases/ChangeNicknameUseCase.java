package com.stubee.memberapplication.usecases;

import com.stubee.memberapplication.commands.ChangeNicknameCommand;

public interface ChangeNicknameUseCase {

    void update(ChangeNicknameCommand command);

}