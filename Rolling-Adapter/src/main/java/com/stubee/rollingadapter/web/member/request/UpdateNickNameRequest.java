package com.stubee.rollingadapter.web.member.request;

import com.stubee.rollingcore.domain.member.command.UpdateNickNameCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateNickNameRequest(
        @NotBlank String nickName) {
    public UpdateNickNameCommand toCommand() {
        return UpdateNickNameCommand.builder()
                .nickName(nickName)
                .build();
    }
}