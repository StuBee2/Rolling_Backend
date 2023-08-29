package com.stubee.memberapplication.commands;

public record ChangeNicknameCommand(
        String nickname) {
    public static ChangeNicknameCommand create(final String nickname) {
        return new ChangeNicknameCommand(nickname);
    }
}