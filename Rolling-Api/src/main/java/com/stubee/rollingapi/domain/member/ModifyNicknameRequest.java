package com.stubee.rollingapi.domain.member;

import com.stubee.memberapplication.usecases.command.ModifyNicknameCommand;
import jakarta.validation.constraints.NotBlank;

record ModifyNicknameRequest(
        @NotBlank String nickName) {
    public ModifyNicknameCommand toCommand() {
        return ModifyNicknameCommand.create(nickName);
    }
}