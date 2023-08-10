package com.stubee.rollingapi.domain.member.request;

import com.stubee.rollingusecases.domain.member.commands.UpdateNickNameCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateNickNameRequest(
        @NotBlank String nickName) {
    public UpdateNickNameCommand toCommand() {
        return UpdateNickNameCommand.create(nickName);
    }
}