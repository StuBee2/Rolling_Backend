package com.stubee.rollingapi.domain.member.request;

import com.stubee.rollingusecases.domain.member.commands.ChangeNicknameCommand;
import jakarta.validation.constraints.NotBlank;

public record ChangeNicknameRequest(
        @NotBlank String nickName) {
    public ChangeNicknameCommand toCommand() {
        return ChangeNicknameCommand.create(nickName);
    }
}