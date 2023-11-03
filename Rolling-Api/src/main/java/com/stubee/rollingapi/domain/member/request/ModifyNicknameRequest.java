package com.stubee.rollingapi.domain.member.request;

import com.stubee.memberapplication.usecases.command.ModifyNicknameCommand;
import jakarta.validation.constraints.NotBlank;

public record ModifyNicknameRequest(
        @NotBlank String nickName) {
    public ModifyNicknameCommand toCommand() {
        return ModifyNicknameCommand.create(nickName);
    }
}