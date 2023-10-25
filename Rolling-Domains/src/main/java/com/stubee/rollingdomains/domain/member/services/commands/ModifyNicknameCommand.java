package com.stubee.rollingdomains.domain.member.services.commands;

public record ModifyNicknameCommand(
        String nickname) {
    public static ModifyNicknameCommand create(final String nickname) {
        return new ModifyNicknameCommand(nickname);
    }
}