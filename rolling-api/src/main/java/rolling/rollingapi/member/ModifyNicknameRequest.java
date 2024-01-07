package rolling.rollingapi.member;

import jakarta.validation.constraints.NotBlank;
import rolling.application.member.interactor.command.ModifyNicknameCommand;

record ModifyNicknameRequest(
        @NotBlank String nickName) {
    public ModifyNicknameCommand toCommand() {
        return new ModifyNicknameCommand(nickName);
    }
}