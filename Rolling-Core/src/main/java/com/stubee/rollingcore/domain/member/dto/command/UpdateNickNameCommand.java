package com.stubee.rollingcore.domain.member.dto.command;

import lombok.Builder;

@Builder
public record UpdateNickNameCommand(
        String nickName) {}