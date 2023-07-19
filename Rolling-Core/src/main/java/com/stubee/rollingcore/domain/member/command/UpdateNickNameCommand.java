package com.stubee.rollingcore.domain.member.command;

public record UpdateNickNameCommand(
        String nickName) {
    public static UpdateNickNameCommand create(final String nickName) {
        return new UpdateNickNameCommand(nickName);
    }
}