package com.stubee.rollingcore.domain.member.command;

import lombok.Builder;

@Builder
public record UpdateNickNameCommand(
        String nickName) {}