package com.stubee.memberapplication.usecases.command;

public record ModifyNicknameCommand(
        String nickname) {
    public static ModifyNicknameCommand create(final String nickname) {
        return new ModifyNicknameCommand(nickname);
    }
}