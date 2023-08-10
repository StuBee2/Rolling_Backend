package com.stubee.rollingusecases.domain.member.commands;

public record UpdateNickNameCommand(
        String nickName) {
    public static UpdateNickNameCommand create(final String nickName) {
        return new UpdateNickNameCommand(nickName);
    }
}