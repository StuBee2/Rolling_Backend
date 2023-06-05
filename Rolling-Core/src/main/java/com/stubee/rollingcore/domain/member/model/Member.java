package com.stubee.rollingcore.domain.member.model;

import com.stubee.rollingcore.domain.member.enums.MemberRole;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record Member (
        UUID id,
        SocialDetails socialDetails,
        String nickName,
        MemberRole memberRole,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {}