package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.MemberRole;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberDetails(
        String nickName,
        MemberRole memberRole,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}