package com.stubee.rollingapplication.domain.member.command;

import lombok.Builder;

@Builder
public record UpdateNickNameCommand(
        String nickName) {}